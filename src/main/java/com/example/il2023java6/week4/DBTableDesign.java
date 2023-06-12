package com.example.il2023java6.week4;

/**
 * 1. why use DB sequence to generate primary key ?
 *      user1 => insert (pk: 123)
 *      user2 => insert (pk: 123)
 * 2. what is foreign key
 *      employee table  (emp_id(pk), emp_name, dept_id(fk))
 *      department table (dept_id(pk), dept_name)
 * 3. super key, candidate key,  primary key
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 * 1. one employee belongs to diff departments, and one dept has multiple employees
 *      emp m - m dept
 *      emp  id(pk), name
 *      emp_dept  emp_id(fk), dept_id(fk)
 *      dept   id(pk), name
 *  2. one dept has multiple employees
 *      dept 1 - m emp
 *      dept   id(pk), name
 *      emp    id(pk), name, dept_id(fk)
 *  3.  dept 1 - 1 emp
 *  4.  entity-attribute-value
 *      document_details table
 *      id, column_name, column_type, value, document_id
 *      1 ,   "name"   ,   "string" , "Tom",   doc_1
 *      2 ,   "age"    ,   "number" ,  5   ,   doc_1
 *
 *      document table
 *         id,       name,       description
 *       doc_1
 *  5.  document1(id, name, age, col1, col2)
 *      document2(id, name, age, col3, col4, col5)
 *
 *      table1(id, name, age)
 *      table2(col1, col2)
 *      table3(col4, col5)
 *  6.  document(id, name, age, col1, col2, col3.....col20)
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *    normalization
 *  1. 1st normal form
 *      id,      name
 *      1,  first/middle/last
 *  2. 2nd normal form
 *      emp
 *      id(pk), name, phone
 *      1     , Tom,  num1
 *      1     , Tom,  num2
 *      id -> name
 *      id -X-> phone
 *  3. 3rd normal form
 *      emp
 *      id(pk), name, address_id, state, ..
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *
 *    In java
 *    dept class 1 - m emp class
 *    class Dept {
 *        private String id;
 *        private String name;
 *        private List<Emp> empList;
 *    }
 *    class Emp {
 *        private String id;
 *        private String name;
 *        private Dept dept;
 *    }
 *
 *    dept class m - m emp class
 *    solution1:
 *    class Dept {
 *        private String id;
 *        private String name;
 *        private List<Emp> empList;
 *    }
 *    class Emp {
 *        private String id;
 *        private String name;
 *        private List<Dept> deptList;
 *    }
 *
 *    solution2:
 *    emp 1 - m empDept m - 1 dept
 *    class Dept {
 *        private String id;
 *        private String name;
 *        private List<EmpDept> empDeptList;
 *    }
 *    class EmpDept {
 *        private String id;
 *        private Dept dept;
 *        private Emp emp;
 *    }
 *    class Emp {
 *        private String id;
 *        private String name;
 *        private List<EmpDept> empDeptList;
 *    }
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   jdbc code
 *
 *
 */