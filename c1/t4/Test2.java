package com.vsu.c1.t4;

/**
 * Created by vsu on 2018/01/27.
 */


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1
 * 使用Condition实现等待/通知
 * Condition可以选择性地进行线程通知
 */
class Service2a{
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void await1(){
        try {
            lock.lock();
            System.out.println("await时间为："  + System.currentTimeMillis());
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signal1(){
        try{
            lock.lock();
            System.out.println("signal时间为：" +System.currentTimeMillis());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}

class MyThread2a extends Thread{
    private Service2a service2a = null;

    public MyThread2a(Service2a service2a){
        super();
        this.service2a = service2a;
    }

    @Override
    public void run(){
        service2a.await1();
    }
}



public class Test2 {
    public static void main(String[] args) {
        /**
         * 1
         */
        try {
            t1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1
     */
    private static void t1() throws InterruptedException {
        Service2a service2a = new Service2a();
        MyThread2a myThread2a = new MyThread2a(service2a);
        myThread2a.start();
        Thread.sleep(3000);
        service2a.signal1();
        /*
        await时间为：1517024285081
        signal时间为：1517024288081
         */
    }

}
