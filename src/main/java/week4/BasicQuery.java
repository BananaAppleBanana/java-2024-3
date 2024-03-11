package week4;

/**
 * -- RDBMS: Oracle, MySQL, PostgreSQL, SQL Server,  RDS(AWS), Aurora(AWS)
 * -- NoSQL: MongoDB, Cassandra, DynamoDB
 * -- In-memory RDBMS: Derby, H2
 * -- Cache: Redis, Memcache, ElasticCache(AWS)
 * -- Object Storage: S3(immutable) -> life cycle -> S3 Glacier(archived data storage)
 * -- File Storage: EFS (file)
 * -- Document DB: (index)ElasticSearch, OpenSearch(AWS)
 * -- Graph DB:
 * -- Data warehouse: Redshift
 *
 * -- 1. query
 * -- 2. transaction
 * -- 3. index
 * -- 4. db tuning
 * -- 5. normalization level, table design
 *
 *
 * -- select *
 * -- from hr.employees
 *
 * -- select *
 * -- from hr.departments
 *
 * -- select first_name as fn
 * -- from hr.empLoyees
 *
 * -- select first_name || last_name as name
 * -- from hr.employees
 *
 * -- select first_name, last_name
 * -- from hr.employees
 *
 * -- select first_name, salary
 * -- from hr.employees
 * -- where salary > 10000
 * -- order by salary, first_name
 *
 *
 * -- select t.first_name
 * -- from (select * from hr.employees) t
 *
 *
 * --aggregation function  (avg, min, max, count, sum)
 * -- select max(salary)
 * -- from hr.employees
 *
 * -- select count(*)
 * -- from hr.employees
 *
 * -- select count(COMMISSION_PCT)
 * -- from hr.employees
 *
 * -- select min(salary)
 * -- from hr.employees
 *
 * -- select sum(salary)
 * -- from hr.employees
 *
 * -- select avg(salary)
 * -- from hr.employees
 *
 * -- find second highest salary from employee table
 * -- SELECT temp.salary
 * -- FROM (
 * --     SELECT DISTINCT salary
 * --     FROM employee
 * --     ORDER BY salary DESC
 * --     LIMIT 2
 * -- ) AS temp
 * -- LIMIT 1 OFFSET 1;
 *
 * -- select MAX(salary)
 * -- from hr.employees
 * -- where salary < (select MAX(salary) from hr.employees)
 *
 * -- select max(salary)
 * -- from hr.employees
 * -- where salary < (select max(salary) from hr.employees)
 * -- 17000
 *
 *
 * -- select max(salary)
 * -- from hr.employees
 * -- where salary < 24000
 *
 * -- select e1.*
 * -- from hr.employees e1
 * -- where 1 = (select count(distinct(salary)) from hr.employees e2 where e2.salary > e1.salary)
 *
 * -- select t.*
 * -- from (select e1.*, dense_rank() over (order by salary desc) as rank
 * --       from hr.employees e1) t
 * -- where rank = 2
 *
 *
 * -- select department_id, count(*)
 * -- from hr.employees
 * -- group by department_id
 * -- having count(*) >= 2
 *
 * -- id,  name
 * -- 1 ,  a
 * -- 2 ,  a
 * -- 3,   b
 * -- 4,   c
 * -- 5,   a
 * -- select name, id
 * -- group by name -> result set -> row?
 *
 * -- select *
 * -- from (select e1.department_id, e1.salary, dense_rank() over (partition by department_id order by salary desc) as rank
 * -- from hr.employees e1)
 * -- where rank = 3
 *
 * --intersect, union, union all, minus
 * -- Union all(A + B), Union(Distinct(A + B))
 * select count(*)
 * from (
 *     	select * from hr.employees
 *     	union
 *     	select * from hr.employees
 *     )
 *
 *
 * select count(*)
 * from (
 *     	select * from hr.employees
 *     	union all
 *     	select * from hr.employees
 *     )
 *
 *
 * select count(*)
 * from (
 *     	select first_name from hr.employees
 *     	union
 *     	select last_name from hr.employees
 *     )
 *
 * --intersect common part(A, B)
 * select count(*)
 * from (
 *     	select * from hr.employees  where employee_id = 101
 *     	intersect
 *     	select * from hr.employees  where employee_id = 102
 *     )
 * -- A minus B =  A - common part(A, B)
 *
 * select count(*)
 * from (
 *     	select * from hr.employees  where employee_id = 101
 *     	minus
 *     	select * from hr.employees  where employee_id = 102
 *     )
 *
 *
 * -- given employee table -> delete duplicate row
 * case1:
 *     employee
 *     id(primary), name,  salary
 * 		1	   ,  a	 ,   101
 * 		2	   ,  a  ,   101
 *  		3      ,  b  ,   101
 * 	remove dup
 * 		1	   ,  a	 ,   101
 * 		3      ,  b  ,   101
 * case2:
 * 	employee
 *     name,  salary
 * 	a	,   101
 * 	a   ,   101
 *  	b   ,   101
 * 	remove dup
 * 	a   ,   101
 * 	b   ,   101
 *
 * homework:
 * 1. create employee table case 1 / case 2
 * 2. insert data
 * 3. delete dup case1 / delete dup case2
 *
 * READ ME
 */