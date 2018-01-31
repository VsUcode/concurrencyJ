package com.vsu.c1.t4;

/**
 * Created by vsu on 2018/01/27.
 */


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1
 * 使用多个Condition实现通知部分线程
 */
class Service3a{
    private Lock lock = new ReentrantLock();
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();

    public void awaitA(){
        try {
            lock.lock();
            System.out.println("begin awaitA= " + System.currentTimeMillis() + " name = " + Thread.currentThread().getName());
            conditionA.await();
            System.out.println("end awaitA= " + System.currentTimeMillis() + " name = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void awaitB(){
        try {
            lock.lock();
            System.out.println("begin awaitB= " + System.currentTimeMillis() + " name = " + Thread.currentThread().getName());
            conditionB.await();
            System.out.println("end awaitB= " + System.currentTimeMillis() + " name = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signalAllA(){
        try{
            lock.lock();
            System.out.println("signalAllA = " + System.currentTimeMillis() + " name = " + Thread.currentThread().getName());
            conditionA.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public void signalAllB(){
        try{
            lock.lock();
            System.out.println("signalAllB = " + System.currentTimeMillis() + " name = " + Thread.currentThread().getName());
            conditionB.signalAll();
        }finally {
            lock.unlock();
        }

    }

}

class MyThread3a extends Thread{
    private Service3a service3a = null;

    public MyThread3a(Service3a service3a){
        super();
        this.service3a = service3a;
    }

    @Override
    public void run(){
        service3a.awaitA();
    }
}


class MyThread3b extends Thread{
    private Service3a service3a = null;

    public MyThread3b(Service3a service3a){
        super();
        this.service3a = service3a;
    }

    @Override
    public void run(){
        service3a.awaitB();
    }
}


public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 1
         */
        t1();
    }

    /**
     * 1
     */
    private static void t1() throws InterruptedException {
        Service3a service3a = new Service3a();
        MyThread3a myThread3a = new MyThread3a(service3a);
        myThread3a.setName("A");
        myThread3a.start();

        MyThread3b myThread3b = new MyThread3b(service3a);
        myThread3b.setName("B");
        myThread3b.start();

        Thread.sleep(3000);
        service3a.signalAllA();
        /* 只唤醒了A：
        begin awaitA= 1517027677727 name = A
        begin awaitB= 1517027677728 name = B
        signalAllA = 1517027680728 name = main
        end awaitA= 1517027680728 name = A
         */
    }


}
