package com.vsu.c1.t1;

/**
 * Created by vsu on 2017/11/28.
 */


/**
 *  线程安全 synchronized
 */
public class Test5 {

    static class MyThread5 implements Runnable{

        private int count = 5;

        @Override
        synchronized public void run() {
            count--;
            System.out.println("name " + Thread.currentThread().getName() + " " + count);
        }
    }

    public static void main(String[] args) {
        MyThread5 myThread5 = new MyThread5();
        Thread t1 = new Thread(myThread5, "A");
        Thread t2 = new Thread(myThread5, "B");
        Thread t3 = new Thread(myThread5, "C");
        t1.start();
        t2.start();
        t3.start();
        /*
        name A 4
        name B 3
        name C 2
         */
    }
}
