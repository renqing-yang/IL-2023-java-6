package com.example.il2023java6.week4.demo3;

import jakarta.persistence.*;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 *  Hibernate
 *  1. datasource
 *  2. hql => centralized query
 *  3. object mapping
 *  4. connection pool
 *  5. provides JPA implementation
 *      session + session factory + save() + saveOrUpdate() + hql
 *      entity manager + entity manager factory + persist() + merge() + jpql
 *  JPA
 *  1. another standard
 *
 *  Hibernate issues
 *  1. N + 1 issues
 *      all lazy loading
 *      List<Student> stus = ..   => 1 query
 *      for(Student s: stus) {
 *          System.out.println(s.getRelation().size());  =>
 *      }
 *    how to solve it
 *      a. eager loading
 *      b. fetch keyword with join (lazy loading)
 *  2.
 */
public class MyJPADemo {

    private DataSource getDataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setDatabaseName("OrmDemo");
        dataSource.setUser("postgres");
        dataSource.setPassword("password");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/university");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
        properties.put( "hibernate.connection.driver_class", "org.postgresql.Driver" );
        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com/example/il2023java6/week4/demo3");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "demo-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    public static void main(String[] args) {
        MyJPADemo jpaDemo = new MyJPADemo();
        DataSource dataSource = jpaDemo.getDataSource();
        Properties properties = jpaDemo.getProperties();
        EntityManagerFactory entityManagerFactory = jpaDemo.entityManagerFactory(dataSource, properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();
        getStudentById(em);

    }

    private static void insertToStudent(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = new Student();
        s.setName("Jerry");
        s.setId("17");
        em.merge(s);
        /*
            if 17 in table
                update table set name = 'jerry' where id = 17
            else
                em.persist(student without id)
         */
        tx.commit();
    }

    private static void getStudentById(EntityManager em) {
        Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
        //lazy loading query : Hibernate: select s1_0.id,s1_0.name from student s1_0 join teacher_student t1_0 on s1_0.id=t1_0.s_id where s1_0.id=?
        query.setParameter(1, "14");
        Student s = (Student)query.getSingleResult();
        System.out.println(s);
        System.out.println(s.getTeacher_students().size());
    }

    private static void addToJunctionTable1(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = new Student();
        s.setName("1th stu");
        Teacher t = new Teacher();
        //persist t first to get new id
        em.persist(t);
        t.setName("1th teacher");
        //build connection between t and s
        Teacher_Student ts = new Teacher_Student();
        ts.setStu(s);
        ts.setTeacher(t);
        t.addTeacher_students(ts);

        em.persist(s);
        tx.commit();
    }

    private static void addToJunctionTable2(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createNativeQuery("INSERT INTO TEACHER_STUDENT (S_ID, T_ID) VALUES (?, ?)");
        query.setParameter(1, 4);
        query.setParameter(2, 4);
        query.executeUpdate();
        tx.commit();
    }

    private static void addToJunctionTable3(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = em.find(Student.class, "14");
        Teacher t = em.find(Teacher.class, "9");
        Teacher_Student ts = new Teacher_Student();
        ts.setStu(s);
        ts.setTeacher(t);
        em.persist(ts);
        tx.commit();
    }

    private static void notOrphanRemoveBiRelation(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "5");
        Student s = (Student) query.getSingleResult();
        Teacher t = em.find(Teacher.class, "3");
        List<Teacher_Student> teacher_students = new ArrayList<>();
        for(Teacher_Student ts: s.getTeacher_students()) {
            if(ts.getTeacher().getId().equals(t.getId())) {
                teacher_students.add(ts);
                em.remove(ts);
            }
        }
        s.getTeacher_students().removeAll(teacher_students);
        t.getTeacher_students().removeAll(teacher_students);
        tx.commit();
    }

    private static void notOrphanRemoveSingleRelation(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "5");
        Student s = (Student) query.getSingleResult();
        for(Teacher_Student ts: s.getTeacher_students()) {
            em.remove(ts);
        }
        s.setTeacher_students(new ArrayList<>());
        tx.commit();
    }

    private static void orphanRemove(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "14");
        Student s = (Student) query.getSingleResult();
        Iterator<Teacher_Student> itr = s.getTeacher_students().iterator();
        while(itr.hasNext()) {
            Teacher_Student ts = itr.next();
            if(ts.getTeacher().getId().equals("9")) {
                itr.remove();
            }
        }
        tx.commit();
    }


    private static void withoutOrphanRemove(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "14");
        Student s = (Student) query.getSingleResult();
        Iterator<Teacher_Student> itr = s.getTeacher_students().iterator();
        while(itr.hasNext()) {
            Teacher_Student ts = itr.next();
            if(ts.getTeacher().getId().equals("9")) {
                itr.remove();
                em.remove(ts);
            }
        }
        tx.commit();
    }
}