package com.example.il2023java6.week4.demo1;

import java.sql.*;

/**
 * PL/SQL(in rdbms database)  : stored procedure / function / trigger / package
 *      1. you can write logic in database
 *              control cursor
 *              write for loop
 *              check exception
 *              handle transaction
 *              write algorithm
 *              ...
 *     2. why PL/SQL
 *              advantages
 *              1. centralized logic in db, can trigger it from any other places
 *              2. faster
 *              disadvantages
 *              1. hard to migrate database
 *              2. hard to debug
 *
 * SQL Injection
 *      what is
 *      "select .. from where username = " + userNameInput + "and password = " + userPwdInput;
 *
 *      userNameInput : "'x' or true"
 *      userPwdInput : "'x' ;drop table"
 *      how to solve
 *      1. validation
 *      2. preparedStatement / convert input into string , parse input
 *              select .. from where username =  'x' or true and password = 'x' ;drop table
 *              select .. from where username =  "\'x\' or true" and password = "\'x\' ;drop table"
 *
 * username, password, url, database name
 * 1. why do we need to close resources
 *      memory leak?
 *          jprofiler
 *          java mission control
 *          jconsole
 *          memory analyzer
 *          => heap dump : snapshot of heap
 *          => analyze memory leak from heap dump
 *     how to identity memory leak
 *          1. check gc activities
 *          2. get out of memory error:
 *                  [eden][s0][s1]  young gen
 *                  [            ]  old gen
 *                  [            ]  meta space (native heap)
 *                  why ?
 *                      heap full
 *                  how to solve it
 *                      1. restart jvm
 *                      2. check memory leak => solve memory leak
 *                      3. gc algorithm
 *                      4. java -XX / change jvm gen size
 *                      5. change reference type
 *                            strong reference
 *                            soft reference : when jvm is full, remove all objects pointed by soft reference
 *                            weak reference :
 *                            phantom reference + reference queue
 *                      6. increase memory size (hardware level)
 *
 * 2. why do we put close() in finally block
 *
 *  Today:
 *        1. configure local database
 *        2. learn hibernate
 *              1. lazy loading, eager loading
 *                  relations in hibernate @OneToMany, @ManyToOne
 *              2. how to use hibernate
 *              3. why use hibernate
 *              4. spring data jpa
 *
 */
public class JdbcExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //STEP 2: Register JDBC driver -> DriverManager
            Class.forName("com.mysql.jdbc.Driver"); //load class object => static block

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT .... first, last, age FROM Employees";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            conn.commit();
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main
}
