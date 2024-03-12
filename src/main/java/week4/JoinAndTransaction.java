package week4;


/**
 * --cross join, inner join, outer join
 * -- A		B
 * -- a		d
 * -- b		e
 * -- c
 *
 * -- select * from A, B
 * -- a  	d
 * -- a	e
 * -- b	d
 * -- b	e
 * -- c	d
 * -- c 	e
 *
 * -- select * from hr.employees
 * select * from hr.departments
 *
 * -- select e.first_name, e.last_name, e.department_id, d.department_name
 * -- from hr.employees e, hr.departments d
 * -- where e.department_id = d.department_id
 *
 * -- select count(*) from (
 * -- select e.first_name, e.last_name, e.department_id, d.department_name
 * -- from hr.employees e inner join hr.departments d on e.department_id = d.department_id)
 *
 * --outer join: left join / right join / full join
 *
 * -- select count(*) from (
 * -- select e.first_name, e.last_name, e.department_id, d.department_name
 * -- from hr.employees e left join hr.departments d on e.department_id = d.department_id)
 *
 *
 * --hr.departments:  DEPARTMENT_ID(primary_key) DEPARTMENT_NAME	MANAGER_ID	LOCATION_ID
 * --hr.employees: employee_id(primary_key), first_name, last_name, salary, department_id(foreign_key)
 *
 * --count employee number in each department: department_id, department_name, number of employees
 *
 * -- SELECT d.DEPARTMENT_ID, d.DEPARTMENT_NAME, COUNT(e.employee_id) AS number_of_employees
 * -- FROM hr.departments d
 * -- LEFT JOIN hr.employees e
 * -- ON d.DEPARTMENT_ID = e.department_id
 * -- GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME;
 *
 * -- select d.DEPARTMENT_ID, d.DEPARTMENT_NAME, ee.counte
 * -- from hr.departments d
 * -- inner join
 * -- (select e.department_id eid, count(e.employee_id) counte from hr.employees e group by e.department_id) ee on d.DEPARTMENT_ID = ee.eid;
 *
 *
 * -- SELECT d.department_id, d.department_name, COUNT(e.employee_id) AS number_of_employees
 * -- FROM hr.departments d
 * -- LEFT JOIN hr.employees e ON d.department_id = e.department_id
 * -- GROUP BY d.department_id, d.department_name;
 *
 *
 *
 *
 * --hr.departments:  DEPARTMENT_ID(primary_key) DEPARTMENT_NAME	MANAGER_ID	LOCATION_ID
 * --hr.employees: employee_id(primary_key), first_name, last_name, salary, department_id(foreign_key)
 * --get 2nd highest salary in each department
 *
 *
 * -- SELECT rk.department_id, rk.salary
 * -- FROM (
 * --     SELECT department_id, salary, DENSE_RANK() OVER (PARTITION BY department_id ORDER BY salary DESC) as rank
 * --     FROM hr.employees e
 * -- ) rk
 * -- WHERE rank = 2;
 *
 *
 * ----------view
 * -- create view my_view as select * from hr.employees
 *
 * -- select * from my_view  =  select * from (select * from hr.employees) = select * from hr.employees
 *
 * -----------material view
 *
 *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   Transaction
 *   User2     select              ==                    select
 *               |                                          |
 *   ----------------------------------------------------------------------------------------> timeline
 *   User1              insert 10 rows   insert 10 rows    insert 10 rows  update 10 rows
 *
 *   Atomicity:
 *   Consistency
 *   Isolation Level: MySQL(Read Uncommitted, Read Committed,  Repeatable Read(default), Serializable)  Oracle (Read Committed(default), Serializable, Read Only)
 *   Durability
 *
 *
 *  Read Uncommitted (problems: dirty read, non-repeatable read,  phantom read) / record lock
 *   User2     select                  !=                         select
 *               |                                                  |
 *   ----------------------------------------------------------------------------------------> timeline
 *   User1          begin tx       insert / update / delete                 commit
 *
 *
 *  Read Committed (problems: non-repeatable read,  phantom read) / record lock
 *   User2     select                  ==                         select
 *               |                                                  |
 *   ----------------------------------------------------------------------------------------> timeline
 *   User1          begin tx       insert / update / delete                 commit
 *
 *   User2   tx  select                  !=                                                select(get updated data)
 *                |                                                                          |
 *   --------------------------------------------------------------------------------------------------> timeline
 *   User1          begin tx       insert / update / delete                 commit
 *
 *
 *  Repeatable Read (problems: phantom read) / next key lock = record lock + gap lock
 *   User2   tx  select                  ==                                                select
 *                |                                                                          |
 *   --------------------------------------------------------------------------------------------------> timeline
 *   User1          begin tx          update / delete                 commit
 *
 *   User2   tx  select                  !=                                                select(get inserted data, 10)
 *                |                                                                          |
 *   --------------------------------------------------------------------------------------------------> timeline
 *   User1           begin tx          insert 10               commit
 *
 *
 *  Serializable  (default select => share lock)
 *
 *   User2   tx  select                  ==                                                select
 *                |                                                                          |
 *   --------------------------------------------------------------------------------------------------> timeline
 *   User1          begin tx          insert/update / delete                 commit
 *
 *
 *
 *  insert / update / delete => default -> write lock / exclusive lock
 *  select => default(before serializable) -> no lock
 *  select... for update  -> write lock (block write lock / read lock)
 *  select ... share -> read lock (block write lock)
 *
 *  table
 *  1      xx
 *  10     xx
 *  13     xx
 *  select .. [1, 10] for update
 *  1  xx  record lock
 *  (1, 10) / [2, 9] gap lock
 *  10 xx  record lock
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  MVCC -> multi version concurrency control
 *
 *  employee table
 *  emp_id,  name,   row_id,  tx_id,  rollback_pointer
 *      1     a
 *      2     b
 *
 *  update a -> c
 *  emp_id,  name,   row_id,  tx_id,  rollback_pointer
 *      1     c                2            |
 *                                          |
 *                                      emp_id,  name,   row_id,  tx_id,  rollback_pointer
 *                                          1      a                1
 *      2     b
 *
 *
 *  Read Committed Isolation Level
 *      select -> generate new read view(committed tx id range)
 *      select -> generate new read view(committed tx id range)
 *
 *  Repeatable
 *      select -> generate new read view(committed tx id range)
 *      select -> reuse previous read view
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Lost Update (optimistic lock)
 *      user1               user2
 *     a = 10               a = 10
 *      +1                    +2
 *     get lock             block
 *     a = 11
 * commit + release lock
 *                         get lock
 *                         a = 12
 *                         commit + release lock
 *
 *      a ,   timestamp/ version
 *     10          2
 *
 *      user1               user2
 *     a = 10, v1           a = 10, v1
 *      +1                    +2
 *     get lock             block
 * update set .. = xx from
 *   where version = 1
 *      a = 11
 *      v = 2
 * commit + release lock
 *                         get lock
 *                         update query -> version != 1
 *                         rollback / return
 *
 *                         a = 11,  v = 2
 *                         ...
 *                         +2
 *                         commit
 *   *   *   *   *   *   *   *   *   *   *   *
 *
 *     frontend  user1          frontend  user2
 *      read document1           read document2
 *            |                         |
 *         request                    request
 *         update doc1                update doc2
 *
 *
 *
 *
 */