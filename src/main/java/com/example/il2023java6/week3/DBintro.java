package com.example.il2023java6.week3;

/**
 * Data storage
 * RDBMS: MySql, PostgreSql, Oracle, Sql server, Sqlite ,Aurora, RDS
 * In-memory RDBMS: Derby, H2
 * NoSQL: MongoDB, Cassandra, DynamoDB
 * Object storage: S3
 * Cache: Redis, Elastic Cache
 * File: EFS
 * Document: Elastic Search, OpenSearch(ES)
 *
 * ------------------------------------------------------------------------------------------------
 * diff RDBMS vs NoSQL
 * 1. schema / table structure
 * 2. normalization in RDBMS
 * 3. sharding in NOSQL => CAP
 * 4. transaction(ACID)
 * ------------------------------------------------------------------------------------------------
 * select * from hr.employees
 *
 * select first_name as fn, last_name as ln, salary
 * from hr.employees
 * where salary > 10000
 * order by salary desc, first_name asc
 *
 * --aggregation function, max, min, count, sum, avg
 * select max(salary)
 * from hr.employees
 *
 * select count(commission_pct)
 * from hr.employees
 *
 * --subquery
 * select t1.first_name
 * from (select * from hr.employees) t1
 *
 * select
 * from hr.employees e1
 * where (select * from hr.employees e2 where e1.salary .. e2.salary)
 *
 * scan e1
 * row1 => compare with e2
 * row2
 * ---question, write a query to return 2nd highest salary from employees table
 *     solution1
 * select max(salary) from hr.employees where salary != (24000)
 * select max(salary) from hr.employees where salary < (select max(salary) from hr.employees)
 * SELECT MAX(salary) FROM hr.employees WHERE salary < (SELECT MAX(salary) FROM hr.employees);
 *
 * SELECT MAX(salary) AS second_highest_salary
 * FROM hr.employees
 * WHERE salary < (SELECT MAX(salary) FROM hr.employees)
 *
 * 	solution2
 * 2nd highest ,  1 unique salary > 2nd highest salary
 * 3rd highest ,  2 unique salary > 3rd highest salary
 * select e1.*
 * from hr.employees e1
 * where 2 = (select count(distinct(salary)) from hr.employees e2 where e2.salary > e1.salary)
 *
 * 	solution3
 * select e2.*
 * from (select e1.*, dense_rank() over (order by e1.salary desc) as rank
 * 	from hr.employees e1) e2
 * where e2.rank = 2
 *
 *
 *
 * group by
 * select * from hr.employees
 *
 * select department_id, count(*)
 * from hr.employees
 * group by department_id
 * having count(*) > 10
 *
 * select e1.*, dense_rank() over (partition by department_id order by e1.salary desc) as rank
 * from hr.employees e1
 * ----------------------------------------------------------------------------------------
 * inner join
 * outer join : left outer join / right / full
 * cross join
 *
 * select * from hr.departments
 * select * from hr.employees
 *
 * select first_name, last_name, department_name, d.department_id
 * from hr.employees e join hr.departments d on e.department_id = d.department_id
 *
 * select first_name, last_name, department_name, d.department_id
 * from hr.employees e, hr.departments d
 * where e.department_id = d.department_id
 *
 * select *
 * from (select first_name, last_name, department_name, d.department_id
 * from hr.employees e join hr.departments d on e.department_id = d.department_id) e
 *
 * select first_name, last_name, department_name, d.department_id
 * from hr.employees e join hr.departments d on e.department_id = d.department_id
 *
 * A	B
 * 1	4
 * 2	5  => cross join A, B => [1, 4] [1, 5] [2, 4] [2, 5] [3, 4] [3, 5]
 * 3
 * select first_name, last_name, department_name, d.department_id
 * from hr.employees e left join hr.departments d on e.department_id = d.department_id
 * where e.department_id is null
 *
 *
 *
 * -----------question------- count emp number in each department, return dept_name, count.
 * 1. outer join
 * 2. group by
 * 3. count(*) / count(column)
 *
 * select d.department_name, count(e.employee_id) as employee_count
 * from hr.employees e right join hr.departments d on e.department_id = d.department_id
 * group by d.department_id, d.department_name
 * order by employee_count desc;
 *
 * SELECT d.department_name, COUNT(e.first_name) AS count
 * FROM hr.departments d LEFT JOIN hr.employees e ON d.department_id = e.department_id
 * GROUP BY d.department_id, d.department_name;
 * ----------question-------- 3rd highest salary in each department, return dept_name, salary
 * select distinct(dn), salary
 * from (
 *     	select d.department_name as dn, e.salary as salary, dense_rank() over (partition by d.department_id order by e.salary desc) as rank
 * 		from hr.departments d join hr.employees e on d.department_id = e.department_id
 * )
 * where rank = 3
 *
 * select department_name, salary
 * from (
 *   		select d.department_name, e.salary, dense_rank() over (partition by d.department_id order by e.salary desc) as rank
 *    		from hr.departments d join hr.employees e on d.department_id = e.department_id
 * )
 * where rank = 3
 *
 * --------------------------------------------------------
 * union, union all
 * intersect :  common(A, B)
 * minus(except) : A - common(A, B)
 *
 * employees table has 107 rows
 * <= 107
 * select count(*)
 * from (
 *         select salary from hr.employees
 *         union
 *         select salary from hr.employees
 * )
 *
 * = 107 * 2
 * select count(*)
 * from (
 *         select salary from hr.employees
 *         union all
 *         select salary from hr.employees
 * )
 *
 *
 * = 107  common(A, B)
 * select count(*)
 * from (
 *         select employee_id, salary from hr.employees
 *         intersect
 *         select employee_id, salary from hr.employees
 * )
 *
 * A - common(A, B)
 * select count(*)
 * from (
 *         select employee_id, salary from hr.employees
 *         minus
 *         select employee_id, salary from hr.employees
 * )
 *
 * leetcode sql (oracle , mysql) practice
 * transaction (ACID)
 */
