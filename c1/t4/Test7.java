package com.vsu.c1.t4;

/**使用ReentrantReadWriteLock类
 * Created by vsu on 2018/01/29.
 */


/**
 * 类ReentrantLock具有完全互斥排他的效果，即同一时间只有一个线程在执行ReentrantLock.lock方法后面的任务
 * 效率非常低下
 * 读写锁ReentrantReadWriteLock：读，共享锁。写，排它锁
 */


import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1
 *读读共享
 */
class Service7a{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read(){
        try {
            try{
                lock.readLock().lock();
                System.out.println("获得读锁 " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(1000);
            }finally {
                lock.readLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread7a extends Thread{
    private Service7a service7a = null;

    public MyThread7a(Service7a service7a){
        super();
        this.service7a = service7a;
    }

    @Override
    public void run(){
        service7a.read();
    }
}

class MyThread7b extends Thread{
    private Service7a service7a = null;

    public MyThread7b(Service7a service7a){
        super();
        this.service7a = service7a;
    }

    @Override
    public void run(){
        service7a.read();
    }
}


/**
 * 2
 * 写写互斥
 */
class Service7b{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void write(){
        try {
            try{
                lock.writeLock().lock();
                System.out.println("获得写锁 " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(1000);
            }finally {
                lock.writeLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread7c extends Thread{
    private Service7b service7b = null;

    public MyThread7c(Service7b service7b){
        super();
        this.service7b = service7b;
    }

    @Override
    public void run(){
        service7b.write();
    }
}

class MyThread7d extends Thread{
    private Service7b service7b = null;

    public MyThread7d(Service7b service7b){
        super();
        this.service7b = service7b;
    }

    @Override
    public void run(){
        service7b.write();
    }
}


/**
 * 3
 * 读写互斥，写读互斥
 */
class Service7c{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read(){
        try {
            try{
                lock.readLock().lock();
                System.out.println("获得读锁 " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(1000);
            }finally {
                lock.readLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write(){
        try {
            try{
                lock.writeLock().lock();
                System.out.println("获得写锁 " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(1000);
            }finally {
                lock.writeLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread7e extends Thread{
    private Service7c service7c = null;

    public MyThread7e(Service7c service7c){
        super();
        this.service7c = service7c;
    }

    @Override
    public void run(){
        service7c.read();
    }
}

class MyThread7f extends Thread{
    private Service7c service7c = null;

    public MyThread7f(Service7c service7c){
        super();
        this.service7c = service7c;
    }

    @Override
    public void run(){
        service7c.write();
    }
}

public class Test7 {
    public static void main(String[] args) {
        /**
         * 1
         */
//        t1();

        /**
         * 2
         */
//        t2();

        /**
         * 3
         */
        t3();

    }

    /**
     * 1
     */
    private static void t1(){
        Service7a service7a = new Service7a();
        MyThread7a myThread7a = new MyThread7a(service7a);
        myThread7a.setName("A");

        MyThread7b myThread7b = new MyThread7b(service7a);
        myThread7b.setName("B");

        myThread7a.start();
        myThread7b.start();
        /*同时进入：
        获得读锁 A 1517191722565
        获得读锁 B 1517191722565
         */
    }

    /**
     * 2
     */
    private static void t2(){
        Service7b service7b = new Service7b();
        MyThread7c myThread7c = new MyThread7c(service7b);
        myThread7c.setName("C");

        MyThread7d myThread7d = new MyThread7d(service7b);
        myThread7d.setName("D");

        myThread7c.start();
        myThread7d.start();
        /*同一时间只允许一个线程：
        获得写锁 C 151719212 1266
        获得写锁 D 151719212 2266
         */
    }

    /**
     * 3
     */
    private static void t3(){
        Service7c service7c = new Service7c();
        MyThread7e myThread7e = new MyThread7e(service7c);
        myThread7e.setName("E");

        MyThread7f myThread7f = new MyThread7f(service7c);
        myThread7f.setName("F");

        myThread7e.start();
        myThread7f.start();
        /*读写互斥，只要出现写操作的过程就是互斥的：
        获得读锁 E 1517192736262
        获得写锁 F 1517192737274
         */
    }


}
