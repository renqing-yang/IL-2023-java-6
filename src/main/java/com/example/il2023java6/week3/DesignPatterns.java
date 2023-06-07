package com.example.il2023java6.week3;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 *  Singleton
 */
//Eager loading singleton
class MyEagerLoadingSingleton {
    private static final MyEagerLoadingSingleton instance = new MyEagerLoadingSingleton();
    private MyEagerLoadingSingleton() {}

    public static MyEagerLoadingSingleton getInstance() {
        return instance;
    }
}

//lazy loading singleton == double check
class MyLazyLoadingSingleton {
    private static volatile MyLazyLoadingSingleton instance;
    private MyLazyLoadingSingleton() {}

    public static MyLazyLoadingSingleton getInstance() {
        if(instance == null) {
            synchronized (MyLazyLoadingSingleton.class) {
                if(instance == null) {
                    instance = new MyLazyLoadingSingleton();
                }
            }
        }
        return instance;
    }
}

//Enum Singleton

enum EnumSingleton {
    INSTANCE1;
}


/**
 *  Factory
 */
class ObjectFactory {
    public static Object getObj() {
        //
        return new Object();
    }

    public static Object getObject(String input) {
        switch (input) {
            case "a":
                return new Object();
            case "b":
                return new Object();
            default:
                return new Object();
        }
    }
}
/**
 *  Builder
 */
@ToString
class DesignPatternStudent1 {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public DesignPatternStudent1 setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public DesignPatternStudent1 setId(int id) {
        this.id = id;
        return this;
    }

    public static void main(String[] args) {
        DesignPatternStudent1 s1 = new DesignPatternStudent1().setId(1).setName("Tom");
        System.out.println(s1);
    }
}

@Data
@ToString
class DesignPatternStudent2 {
    private String name;
    private int id;

    public DesignPatternStudent2(String name, int id) {
        this.name = name;
        this.id = id;
    }

    static class Builder {
        private String name;
        private int id;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public DesignPatternStudent2 build() {
            return new DesignPatternStudent2(name, id);
        }
    }

    public static void main(String[] args) {
        DesignPatternStudent2 s2 = new DesignPatternStudent2.Builder().setId(2).setName("Tom").build();
        System.out.println(s2);
    }
}
/**
 *  Prototype
 *      A1 => clone A1`
 *      A2 => clone A2`
 */

/**
 *  Template
 *
 *              class Car {
 *                  ...
 *              }
 *
 *  BMW extends Car / Benz extends Car /  Honda extends Car
 */

/**
 *  -> request / data -> Facade class -> class1 / class2 / class3
 */
/**
 *  Composition
 */
class TreeNode<T> {
    private TreeNode left;
    private TreeNode right;
    private T val;
}

/**
 *  Observer
 */
class Topic {
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber sub) {
        subscribers.add(sub);
    }

    public void send(String msg) {
        for(Subscriber sub: subscribers) {
            sub.receive(msg);
        }
    }
}

class Subscriber {
    public void receive(String msg) {
        System.out.println(msg);
    }
}


/**
 *  bridge
 *
 *  class A {
 *      public int get() {
 *          return 5;
 *      }
 *  }
 *
 *  class B {
 *      private A a;
 *      public void print() {
 *          sout(a.get());
 *      }
 *  }
 */

/**
 *  strategy
 *
 *  class A {}
 *
 *  class B {
 *      public void print(A a) {
 *          sout(a.get());
 *      }
 *  }
 */
@FunctionalInterface
interface Calculation {
    int calculate(int a, int b);
}

class Calculator {
    public void print(int a, int b, Calculation calculation) {
        System.out.println(calculation.calculate(a, b));
    }

    public static void main(String[] args) {
        Calculator c1 = new Calculator();
        c1.print(1, 2, (a, b) -> a + b);
    }
}
/**
 *  Adaptor
 */
interface Robot {
    void printRobot();
}
class MyRobot implements Robot {
    @Override
    public void printRobot() {
        System.out.println("i'm robot");
    }
}
class MyRobotAdaptor implements Human {
    private Robot robot;

    public MyRobotAdaptor(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void print() {
        robot.printRobot();
    }
}

interface Human {
    void print();
}
class AdaptorExample {
    public void print(Human human) {
        human.print();
    }

    public static void main(String[] args) {
        Human human = new MyRobotAdaptor(new MyRobot());
        new AdaptorExample().print(human);
    }
}

/**
 *  Static Proxy
 */
class RobotStaticProxy1 implements Robot {
    private Robot robot;

    public RobotStaticProxy1(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void printRobot() {
        System.out.println("before");
        robot.printRobot();
        System.out.println("after");
    }
}

class RobotStaticProxy2 extends MyRobot {
    @Override
    public void printRobot() {
        System.out.println("before");
        super.printRobot();
        System.out.println("after");
    }
}

/**
 *  Dynamic Proxy
 *   *   *   *   *   *   *   *   *   *   *   * *
 *  Has-A vs Is-A
 *
 *
 *  Tomorrow:
 *  database introduction + sql query
 *  Oracle live sql
 */