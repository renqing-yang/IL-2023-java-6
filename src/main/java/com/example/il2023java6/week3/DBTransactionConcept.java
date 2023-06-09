package com.example.il2023java6.week3;

/**
 *       user1  ->   data    <- user2
 *          data1
 *       1. user1 get lock / put lock on this row
 *       2. commit => data1 => data2
 *       3. user2 select data1 or data2
 *
 *       1. user1
 *              update row1
 *              update row2
 *
 *              update row3
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Transaction properties
 *      1. Atomicity
 *      2. Consistency
 *      3. Isolation level
 *              MySql : Read Uncommitted / Read Committed / Repeatable Read(default) / Serializable
 *              Oracle: Read Committed(default) / Serializable / Read Only
 *      4. Durability
 *
 *  Read Uncommitted (problems: dirty read, non-repeatable read, phantom read)
 *  User1   begin tx        insert/update/delete data          rollback data
 *  ------------------------------------------------------------------------------------------> time line
 *  User2       begin tx                           select data(all dirty data / uncommitted changes)
 *
 *
 *  Read Committed (problems: non-repeatable read, phantom read)
 *  User1   begin tx        insert/update/delete data          commit data
 *  ------------------------------------------------------------------------------------------> time line
 *  User2    begin tx                     select data(no dirty data)        select data(most recent update)
 *
 *
 *  Repeatable Read(default,  problems: phantom read)
 *  User1    begin tx      update/delete data         commit data
 *  ------------------------------------------------------------------------------------------> time line
 *  User2       begin tx            select version1        ====        select version1


 *  User1    begin tx      insert data              commit data
 *  ------------------------------------------------------------------------------------------> time line
 *  User2       begin tx            select version1        !=        select version2
 *
 *
 *
 *  Serializable (problems: none)
 *  User1       begin tx      update/delete/insert data         commit data
 *  ------------------------------------------------------------------------------------------> time line
 *  User2            begin tx               select version1        ====        select version1
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  MVCC => multi version concurrency control
 *  1. 3 hidden columns (row_id, tx_id, rollback pointer)
 *  2. undo area
 *  3. select => read view() => committed tx id
 *
 *  student
 *  id  , name,  tx_id, rollback pointer
 *  1   , Tom,      1,  null
 *
 *  update Tom to Jerry
 *
 *  student
 *  id  , name,  tx_id, rollback pointer
 *  1   , Jerry,    2,     |
 *                         |
 *                        1   , Tom,      1,  null
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  exclusive lock(write lock)
 *      1. insert
 *      2. update
 *      3. delete
 *      4. select ... for update
 *  share lock(read lock)
 *      1. select (ru, rc, rr => plain select , no lock select)
 *      2. select (serializable => add read lock on each select)
 *      3. select ... share
 *
 *  optimistic lock (prevent lost update)
 *             users
 *               |
 *              app
 *              |
 *           database
 *
 *       user1: read data = 2           user2: read data = 2
 *                 get ex lock
 *                  data++
 *                 commit
 *                 release ex clock
 *                                              get ex lock
 *                                              data++
 *                                              commit
 *                                              release ex lock
 *
 *       print(data) = 3
 *
 *
 *
 *       user1: read data = 2, version=1           user2: read data = 2, version=1
 *                 get ex lock
 *            update data++, version++
 *            where version = 1
 *                 commit
 *                 release ex clock
 *             (data = 3, version = 2)
 *                                                  get ex lock
 *                                                  update data++, version++  where version = 1 => throw exception
 *                                                  commit
 *                                                  release ex lock
 *                                                  read data = 3, version = 2
 *                                                  ...
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  index
 *  B tree
 *      insert 10
 *      [10]
 *      insert 5
 *      [5, 10]
 *      insert 20
 *      [5, 10, 20]
 *      insert 15
 *      [5, 10, 15, 20]
 *
 *          [15]
 *        /     \
 *     [5, 10]  [15, 20]
 *
 *     insert 30
 *
 *          [15]
 *        /     \
 *     [5, 10]  [15, 20, 30]
 *
 *     insert 40
 *
 *          [15,   30]
 *        /     \        \
 *     [5, 10]  [15, 20]  [30, 40]
 *
 *     insert 50, 60
 *
 *          [15,   30,  50]
 *        /     \       \           \
 *     [5, 10]  [15, 20]  [30, 40]   [50, 60]


 *     insert 70, 80
 *                          [50]
 *                  /               \
 *          [15,   30]                  [50,  70]
 *        /     \       \               /        \
 *     [5, 10]  [15, 20]  [30, 40]   [50, 60]   [70, 80]
 *
 *
 *   B+ tree
 *                          [50]
 *                  /               \
 *          [15,   30]                  [50,  70]
 *        /     \       \               /        \
 *     [5, 10] <->[15, 20]<->[30, 40]<->[50, 60]<->[70, 80]
 *
 *
 *          table                   Bitmap index
 *      id, state  row_id          row_id  NJ   NY   VA
 *      1 , NJ                              1   0     0
 *      2,  NY                              0   1     0
 *      3,  VA                              0   0     1
 *
 *
 *      NJ: 100
 *      NY: 010
 *      VA: 001
 *
 *      NJ or NY => 100 or 010 => 110
 *  ************************
 *  1. Full table scan
 * 2. Index access path
 *     	index unique scan
 *     	index range scan
 *     	index full scan
 *     	index fast full scan
 * 3. join
 * 		hash join
 *     		buckets[]
 *     		hash(join condition)
 *     		add data into bucket[idx = hash % length of array]
 *     		do join in each bucket
 *     	merge join
 *     		get two sorted array / data set
 *     		merge sort
 *     	nested loop join => O(n^2)
 *     		for() {
 * 				for() {
 *
 *                 }
 *             }
 *
 * EXPLAIN PLAN FOR
 *     select * from hr.employees;
 * select plan_table_output from table(dbms_xplan.display('plan_table',null,'basic'));
 *
 * EXPLAIN PLAN FOR
 *     select * from hr.employees where employee_id = 100 or employee_id = 101;
 * select plan_table_output from table(dbms_xplan.display('plan_table',null,'basic'));
 *
 * EXPLAIN PLAN FOR
 *     select * from hr.employees where employee_id >= 100 and employee_id <= 101;
 * select plan_table_output from table(dbms_xplan.display('plan_table',null,'basic'));
 *
 * EXPLAIN PLAN FOR
// *     select /*+ full(e)*/ //*from hr.employees e where employee_id>=100and employee_id<=101;
//        *select plan_table_output from table(dbms_xplan.display('plan_table',null,'basic'));
//        *
//        *
//        *EXPLAIN PLAN FOR
//        *select /*+ leading(e) use_nl(e d) full(d) index(e) */ e.*
//        *from hr.employees e join hr.departments d on e.employee_id=d.department_id;
//        *select plan_table_output from table(dbms_xplan.display('plan_table',null,null));
//        *
//        *
//        *EXPLAIN PLAN FOR
//        *select e.*
//        *from hr.employees e,hr.departments d
//        *where e.employee_id=d.department_id;
//        *select plan_table_output from table(dbms_xplan.display('plan_table',null,null));

/**
 * table design
 *
 *
 * 1. how to improve query performance in database
 * 2. what kind of hints do you know
 * 3. index access path / full table scan / joins
 * 4. what is execution plan
 *
 */