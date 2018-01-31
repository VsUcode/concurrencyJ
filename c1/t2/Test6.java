package com.vsu.c1.t2;

/**
 * Created by vsu on 2018/01/21.
 */


/**
 * 静态同步synchronized方法与synchronized(class)代码块
 * 用在静态方法上，就是对当前class类进行持锁
 */



/**
 * 锁加到static方法上
 */
class Service6{
    synchronized public static void printA(){
        try {
            System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入A");
            Thread.sleep(3000);
            System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开A");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void printB(){
            System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入B");
            System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开B");
    }
}



class Thread6a1 extends Thread{
    @Override
    public void run(){
        Service6.printA();
    }
}

class Thread6a2 extends Thread{
    @Override
    public void run(){
        Service6.printB();
    }
}


/**
 *  2 synchronized加到static静态方法上是给class类上锁，加到非static静态方法上是给对象上锁
 */
class Service6b{
    synchronized public static void printA(){
        try {
            System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入A");
            Thread.sleep(3000);
            System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开A");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void printB(){
        System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入B");
        System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开B");
    }

    synchronized public  void printC(){
        System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入C");
        System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开C");
    }
}



class Thread6b1 extends Thread{
    private Service6b service6b= null;

    public Thread6b1(Service6b service6b){
        super();
        this.service6b = service6b;
    }
    @Override
    public void run(){
        service6b.printA();
    }
}

class Thread6b2 extends Thread{
    private Service6b service6b= null;

    public Thread6b2(Service6b service6b){
        super();
        this.service6b = service6b;
    }
    @Override
    public void run(){
       service6b.printB();
    }
}

class Thread6b3 extends Thread{
    private Service6b service6b= null;

    public Thread6b3(Service6b service6b){
        super();
        this.service6b = service6b;
    }
    @Override
    public void run(){
        service6b.printC();
    }
}


/**
 * 3 静态同步synchronized方法与synchronized(class)代码块作用一样
 */
class Service6c{
    public static void printA(){
        synchronized (Service6c.class){
            try {
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入A");
                Thread.sleep(3000);
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开A");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printB(){
        synchronized (Service6c.class){
            System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入B");
            System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开B");
        }

    }
}



class Thread6c1 extends Thread{
    private Service6c service6c= null;

    public Thread6c1(Service6c service6c){
        super();
        this.service6c = service6c;
    }
    @Override
    public void run(){
        service6c.printA();
    }
}

class Thread6c2 extends Thread{
    private Service6c service6c= null;

    public Thread6c2(Service6c service6c){
        super();
        this.service6c = service6c;
    }
    @Override
    public void run(){
        service6c.printB();
    }
}

public class Test6 {
    public static void main(String[] args) {
        /**
         * static方法
         */
        Thread6a1 thread6a1 = new Thread6a1();
        thread6a1.setName("a");
        thread6a1.start();

        Thread6a2 thread6a2 = new Thread6a2();
        thread6a2.setName("b");
        thread6a2.start();
        /* 同步：
        线程名称为：a 在 1516504113567 进入A
        线程名称为：a 在 1516504116568 离开A
        线程名称为：b 在 1516504116568 进入B
        线程名称为：b 在 1516504116568 离开B

         */

        /**
         * 2
         */
        Service6b service6b = new Service6b();
        Thread6b1 thread6b1 = new Thread6b1(service6b);
        thread6b1.setName("b1");
        thread6b1.start();

        Thread6b2 thread6b2 = new Thread6b2(service6b);
        thread6b2.setName("b2");
        thread6b2.start();

        Thread6b3 thread6b3 = new Thread6b3(service6b);
        thread6b3.setName("b3");
        thread6b3.start();
        /* 异步：一个对象锁，一个class锁，class锁对类所有对象实例起作用
        线程名称为：b1 在 1516505369500 进入A
        线程名称为：b3 在 1516505369501 进入C
        线程名称为：b3 在 1516505369501 离开C
        线程名称为：b1 在 1516505372500 离开A
        线程名称为：b2 在 1516505372500 进入B
        线程名称为：b2 在 1516505372500 离开B
         */


        /**
         * 3
         */
        Service6c service1 = new Service6c();
        Service6c service2 = new Service6c();
        Thread6c1 thread6c1 = new Thread6c1(service1);
        thread6c1.setName("c1");
        thread6c1.start();

        Thread6c2 thread6c2 = new Thread6c2(service2);
        thread6c2.setName("c2");
        thread6c2.start();
        /* 同步：
        线程名称为：c1 在 1516506426446 进入A
        线程名称为：c1 在 1516506429447 离开A
        线程名称为：c2 在 1516506429447 进入B
        线程名称为：c2 在 1516506429447 离开B
         */

    }




























}
