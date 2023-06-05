package com.example.il2023java6.week3;

import java.util.*;

/**
 * Java basic
 * What is oop? What is an object?
 * Polymorphism, encapsulation, inheritance, abstraction
 * Interface vs abstraction
 * when do we use interface , when do we use abstract class ?
 * multi inheritance -> interface
 * changeable variables -> abstract class
 * constructor -> abstract class
 * Overloading vs overriding
 * Java naming principle
 * Package, class, interface, field, constant, method
 * String vs stringbuffer vs string builder
 * Method in Java
 * Access modifiers: public, protected, default, private
 * This vs super
 * What can we do with this keyword and super keyword
 * “This” can be used to reference current object, call constructor, as parameter and constructor parameter,
 * Super can be used to reference to parent class, call parent method, call parent’s constructor
 *
 * Java pass by value
 */

/**
 * list1     ->   [1, 2, 3, 4]
 *
 * list2    ->  []
 */
class PassByValueExample {
    public static void main(String[] args) {
//        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
//        fun1(list1);
//        System.out.println(list1);
//
        //func2();
    }

    public static void fun1(List<Integer> list2) {
        list2 = new ArrayList<>();
    }

    public void func2() {
        System.out.println("func2");
    }
}
/**
 * Primitive type vs wrapper type
 * What are outputs? ->
 * Integer i1 = 200; Integer i2 = 200 -> i1 == i2
 * Integer i1 = 100 ; Integer i2 = 100 -> i1 == i2
 * The range of Integer Cache
 * Shallow copy vs deep copy
 * Static vs non-static
 * Throwable + Checked(Exception class) vs unchecked
 * is throwable a class / interface
 * where do we use throwable
 * Throws vs throw
 * Error vs Exception class
 *
 * throw checked exception => Exception class /
 * throw unchecked exception => RuntimeException class / ..
 *
 * 	Throwable
 * 	/	\
 * Error		Exception
 * 			\
 * 			RuntimeException
 * how to create customized exception
 * checked => extends from Exception
 * unchecked => extends from RuntimeException
 * Generic
 * Java common collections
 * Java array list
 * Random access
 * Resizing function
 * Time complexity
 * Stack + vector
 * Resize method
 * Time complexity
 * Vector vs stack vs arraylist
 * Linked list + deque
 * linkLast function
 * Time complexity
 * Deque practice
 * Implement stack by using deque
 * Queue methods vs deque methods
 * Stack methods vs deque methods
 * Queue
 * priorityQueue
 * Why heap using array to store data
 * Offer function
 * Siftup function
 * Demo an example with deque and priority queue
 *
 * Hashmap
 * How does hashmap do hashing
 * resizing()
 * Put method
 * Example with ThreeMap
 */
class HashMapExample {
    public static void main(String[] args) {
        Map<Week3Student, Integer> m = new HashMap<>();
        //no hashcode()
        Week3Student s1 = new Week3Student(1, "Tom");
        Week3Student s2 = new Week3Student(1, "Tom");
//        System.out.println(s1.equals(s2));
        m.put(s1, 1);
        System.out.println(m.get(s2));
    }
}

class Week3Student {
    private int id;
    private String name;

    public Week3Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Week3Student that = (Week3Student) o;
        return id == that.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Week3Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
/**
 *
 * Examples with set
 * JVM and GC
 * What is JVM?
 * JVM components
 * Class loader
 * Execution engine
 * Runtime data area
 * Method area
 * Heap
 * The components of heap:
 * What will be stored in the heap?
 * young[eden, s0,s1]  => minor gc
 * old generation[] => major gc
 *
 *
 *
 * Stack:
 * What is stack? What are values stored in stack
 *
 *
 * How to determine the object is alive or dead?
 * The referencing counting method
 * GC roots
 * What are things can be gc roots:
 * Local variables, static variables, thread stack, classes and class loader
 * GC algorithm
 * What is STW
 * Normal-deletion: mark-and-sweep
 * Deletion-compacting
 * CMS ? concurrent mark and sweep
 * initial marking (Stop the world)
 * concurrent mark
 * final mark (stop the world)
 * concurrent sweep
 * G1
 * The general steps of G1
 * Initial mark -> concurrent marking -> remarking ->clean up->compaction
 *
 * Multi threading
 * How many ways to create a thread
 * Thread states
 * wait() notify() sleep(), notifyall()
 * Practice with the above functions
 * Volatile
 * What is JMM? What does it do?
 * The functionalities of volatile
 * Synchronized
 * Synchronized and non static method call by one or two thread, Is class locked or object locked
 * Synchronized and static method call by one or two thread, is class locked or object locked
 */
class SynchronizedExample {
    public static void main(String[] args) throws InterruptedException {

    }
    //class object
    public static synchronized void func1() {}
    //this  object
    public synchronized void func2() {}
}
/**
 * Concurrent
 * What is cas?
 * What is atomic operation
 * Practice: atomicInteger calls compareAndSet
 * ABA problem
 * Demo ABA problem
 *
 *  i++
 *
 *  synchronized xx () {
 *      i++;
 *  }
 *
 *  AtomicInteger ..  getAndIncrement(); => CAS => CPU instruction
 */

/**
 * Thread safe collections / concurrent api (fail safe)
 *   1. blocking queue
 *   2. concurrent hashmap / hashtable
 *   3. vector / Stack
 *   4. StringBuffer
 *   5. CopyOnWriteList
 *
 *
 *   fail fast => concurrent modification exception
 */
class FailFastExample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);

        for(int v: list) {
            list.add(3);
        }
    }
}

/**
 * Concurrent hashmap in java7 and java 8
 * Put method
 * Get method
 * Blocking queue
 * What is that + producer-consumer pattern
 * Put method
 * Take method
 * Implement producer-consumer pattern by using blocking queue
 *      1. add element
 *              lock.lock();
 *              try {
 *                  while(queue is full) {
 *                      conditionFull.await();
 *                  }
 *                  add element into queue
 *                  conditionEmpty.signal();
 *              } finally {
 *                  lock.unLock();
 *              }
 *      2. take element
 *              lock.lock();
 *              try {
 *                  while(queue is empty) {
 *                      conditionEmpty.await()
 *                  }
 *                  remove element from queue
 *                  conditionFull.signal();
 *              } finally {
 *                  lock.unlock();
 *              }
 *
 * Drawbacks of synchronized
 * Reentrant lock
 *  * Semaphore
 * What are features it provides
 *
 * What is thread pool
 * Executor vs executor service vs executors
 * Fixed-thread pool
 * Cached thread pool =>
 * Single thread pool
 * Fork-join pool
 *                          t1  [][][]
 *          [][][][][]      t2  [][][]
 *
 * Java 8 new features
 * What is functional interface
 * Lambda expressions
 * Practice with lambda expression: to create callable thread, max heap
 * Stream api
 * Collections vs stream api
 * Some examples with stream apis
 * Intermediate functions
 * filter, map, flatMap, distinct, sorted, limit,skip
 * Code: filter, map, maptoInt,sorted
 * Practice during class
 * Terminal functions:
 * Explain: foreach, reduce, max, min, count, anyMatch, allMatch
 * Code: forEach(), anyMatch, allMatch, reduce, sum,collect
 */
/**
 * Java stream api implementation
 *  stream() => create ReferencePipeline(linked node, next, val, prev)
 *  stream().map().filter() :  ReferencePipeline1 <=> ReferencePipeline2 <=> ReferencePipeline3
 *  stream().map().filter().collect() : ReferencePipeline1 <=> ReferencePipeline2 <=> ReferencePipeline3 <=> ReferencePipeline4
 *                                      Sink1 => Sink2 => Sink3 => Sink4
 */
class StreamExample {
    public static void main(String[] args) {
        new ArrayList<>().stream();
    }
}


//******************************************************************************************************

/**
 * Practice during class
 * Mention a little bit about parallel stream api
 * Optional
 * Use cases
 * Make an example with optional
 * Completable future
 * what is asynchronous computation?
 * Future vs completable future
 * Consumer vs supplier, why do we use them?
 * runAsync vs supplyAsync
 * Demonstrate: runAsync, supplyAsync
 * get() vs join()
 * thenApply vs thenRun vs thenAccept
 * Method reference
 * Where can we use it?
 *
 * Spring Boot+networking
 *
 * What is pom.xml
 * What is dependency
 * What is application.properties
 * What is spring bean
 * What is spring annotation
 * What is maven? What are popular commands?
 * OSI model in networking
 * Application layer, session layer, network layer, transport layer, data link layer, physical layer
 * TCP/IP model in networking
 * Application layer, transport layer, networking layer, network access layer
 * Three way handshake: TCP
 * What is SYN, ACK, seq, FIN, SYN-ACK
 * Why three times?
 * What if the first handshake is lost, what will happen/
 * What if the second handshake is lost, what will happen
 * Four way termination
 * Why four times
 * What will happen, if the first, second, third and fourth handshake is lost
 * What is UDP
 * Steps UDP
 * What is DNS
 * What is http?
 * What is http request message
 * What is http response message
 * What are http methods
 * What are status code we commonly used
 * What is https?
 * What is TLS
 * How TLS works
 * Client hello
 * Server hello
 * Certificate authority
 * Server key exchange
 * Client key exchange
 * What is pre-master and session key
 * What is symmetric and asymmetric algorithm
 * What is spring MVC
 * Model vs view vs controller
 * The benefits of mvc
 * Build a sample project: calculator
 * Demo how to build restController
 * Add function, sub function, multiply function, divide function: four restful apis
 * requestBody vs pathVariable vs requestParam
 * How to do test using postman
 * How to build exception handler
 * What is Json?
 * What are getmapping, PostMapping, PutMapping, DeleteMapping, PatchMapping
 * patchMapping vs putMapping
 * restController vs controller
 * Dispatcher servlet -> handler -> controller -> view resolver ->view
 * What is dispatcher servlet
 * What are components in spring servlet
 * Service layer, repository layer, model, view, controller
 * What is IOC?
 * What is applicationContext?
 * What is beanFactory
 * What is dependency injection
 * Demo beans in applicationContext container
 * Autowired vs qualifier
 * Coding demo for this
 * What are beans life cycles?
 * Cookie vs session in http
 * Bean scope
 * Singleton
 * Prototype
 * Request
 * Session
 * Application
 * How to build a spring mvc project
 * Implement service layer, controller, repo layer
 * How to connect to database
 * What are annotations for Entity, Table, Data, Getter, Setter, Id
 * What are id generate strategy?
 * Auto, identity, sequence, table
 * Field inject vs construction inject vs setter injection
 * SpringAOP
 * Testing
 * TDD test and the steps
 * Unit test vs integration test
 * How to build a test function or class
 * How to test restful api
 * How to use mockMvc
 * How to implement TDD
 * What is spring validation
 * How to use spring validation
 * What are annotations: Valid, Min, Max, Size,email
 * A demo for spring validation
 */
