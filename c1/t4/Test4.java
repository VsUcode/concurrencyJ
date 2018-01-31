package com.vsu.c1.t4;

/** 实现生产者/消费者
 * Created by vsu on 2018/01/27.
 */


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1
 * 一生产一消费
 */
class Service4a{
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();
    private boolean hasValue = false;

    public void set(){
        try {
            reentrantLock.lock();
            while(hasValue == true){
                condition.await();
            }
            System.out.println("1");
            hasValue = true;
            condition.signal();
        }catch (InterruptedException e) {
                e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }

    }

    public void get(){
        try {
            reentrantLock.lock();
            while(hasValue == false){
                condition.await();
            }
            System.out.println("0");
            hasValue = false;
            condition.signal();
        }catch (InterruptedException e) {
                e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }

    }
}


class MyThread4c extends Thread{
    private Service4b service4b = null;

    public MyThread4c(Service4b service4b){
        super();
        this.service4b = service4b;
    }

    @Override
    public void run(){
        for (int i=0; i<Integer.MAX_VALUE; i++){
            service4b.set();
        }
    }
}

class MyThread4d extends Thread{
    private Service4b service4b = null;

    public MyThread4d(Service4b service4b){
        super();
        this.service4b = service4b;
    }

    @Override
    public void run(){
        for (int i=0; i<Integer.MAX_VALUE; i++){
            service4b.get();
        }
    }
}


/**
 * 2
 * 多生产多消费
 */
class Service4b{
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();
    private boolean hasValue = false;

    public void set(){
        try {
            reentrantLock.lock();
            while(hasValue == true){
                System.out.println("11111");
                condition.await();
            }
            System.out.println("1");
            hasValue = true;
            condition.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }

    }

    public void get(){
        try {
            reentrantLock.lock();
            while(hasValue == false){
                System.out.println("00000");
                condition.await();
            }
            System.out.println("0");
            hasValue = false;
            condition.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }

    }
}

class MyThread4a extends Thread{
    private Service4a service4a = null;

    public MyThread4a(Service4a service4a){
        super();
        this.service4a = service4a;
    }

    @Override
    public void run(){
        for (int i=0; i<Integer.MAX_VALUE; i++){
            service4a.set();
        }
    }
}

class MyThread4b extends Thread{
    private Service4a service4a = null;

    public MyThread4b(Service4a service4a){
        super();
        this.service4a = service4a;
    }

    @Override
    public void run(){
        for (int i=0; i<Integer.MAX_VALUE; i++){
            service4a.get();
        }
    }
}




public class Test4 {
    public static void main(String[] args) {
        /**
         * 1
         */
//        t1();

        /**
         * 2
         */
        t2();
    }

    /**
     * 1
     */
    private static void t1(){
        Service4a service4a = new Service4a();
        MyThread4a myThread4a = new MyThread4a(service4a);
        myThread4a.start();

        MyThread4b myThread4b = new MyThread4b(service4a);
        myThread4b.start();
        /*
        0
        1
        0
        1
        0
        1
        0
         */
    }

    /**
     * 2
     */
    private static void t2(){
        Service4b service4b = new Service4b();
        MyThread4c[] myThread4c = new MyThread4c[10];
        MyThread4d[] myThread4d = new MyThread4d[10];

        for (int i=0; i<10; i++){
            myThread4c[i] = new MyThread4c(service4b);
            myThread4d[i] = new MyThread4d(service4b);
            myThread4c[i].start();
            myThread4d[i].start();
        }
        /*
        00000
        00000
        00000
        1
        11111
        11111
        11111
        11111
        0
         */
    }

}
