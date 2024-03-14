package week4;

/**
 *  column A,  column B
 *     a     ->  c
 *     a     ->  c
 *     a     ->  c
 *     b     ->  d
 *     b     ->  d
 *   column A determines column B
 *   column B depends on column A
 *
 *   emp_id, emp_name
 *
 *   super key -> candidate key, candidate key + non prime attributes
 *   candidate key ->
 *   primary key(picked from candidate key set) -> unique not null
 *
 *   super key[.. ,  candidate key[primary key]]
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Normalize
 *  1st normal form
 *         name
 *     first,last
 *     first:last
 *     first-last
 *     first/last
 *  2nd normal form
 *      non prime attributes fully depends on prime attributes
 *
 *      emp_id, dept_id, emp_name,      dept_name
 *        1        1        Alex      accounting
 *        1        2        Alex           hr
 *        2        1        John      accounting
 *
 *      candidate key: emp_id, dept_id
 *      emp_id  ->  emp_name     OK
 *      emp_id  ->  dept_name    X
 *      dept_id ->  emp_name     X
 *      dept_id ->  dept_name    OK
 *
 *      emp_name -> fully depends on prime attributes??  X
 *      dept_name -> fully depends on prime attributes?? X
 *  3rd normal form
 *      non-prime attributes doesn't have transitive relationship
 *
 *      emp_id,  emp_name,  address_id, address_location
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   emp 1 to 1 dept
 *   emp(emp_id(pk), emp_details, dept_id(fk))
 *   dept(dept_id(pk), dept_details)
 *
 *   emp(emp_id(pk), emp_details)
 *   dept(dept_id(pk), dept_details, emp_id(fk))
 *
 *   emp m to 1 dept
 *   emp(emp_id(pk), emp_details, dept_id(fk))
 *   dept(dept_id(pk), dept_details)
 *
 *
 *   emp m to m dept
 *   emp(emp_id(pk), emp_details)
 *   emp_dept(id(pk), emp_id(fk), dept_id(fk))
 *   dept(dept_id(pk), dept_details)
 *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *   survey
 *      version1:  name, age, working exp ...
 *      version2:  name, age, car, fav color
 *
 *   design1
 *      survey1 table 1
 *      survey2 table 2
 *
 *   design2
 *      name, age, working exp, car, fav color ...
 *
 *   design3
 *      parent table
 *       name   age
 *
 *      sub table / child table 1 (..working exp)
 *      sub table / child table 2 (..fav color)
 *
 *   design4
 *      name, age, json data
 *                  ..
 *
 *   design 5 , entity value attribute
 *      table 1
 *     survey table
 *     survey_id, ..
 *        s1
 *        s2
 *
 *     survey_details
 *     id, value, type,   ,  col_name, survey_id
 *      1,  Alex, varchar,    name   ,  s1
 *      2,  25  , number ,    age    ,  s1
 *      3,  "xx", varchar, working exp,  s1
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   PL/SQL
 *      begin
 *          ...
 *      end
 *
 *     1. function
 *          query call function
 *          input -> return
 *     2. stored procedure
 *          transaction
 *          input -> no return
 *     3. trigger
 *          based on table / column
 *          before insert table
 *          after  insert table
 *     4. package
 *          OOD
 *          packageName.stored procedure name
 *
 */
