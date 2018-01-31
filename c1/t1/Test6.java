package com.vsu.c1.t1;

/**
 * Created by vsu on 2017/11/28.
 */


/**
 *  留意i-- 和 System.out.println();
 */
public class Test6 {

    /**
     *  线程安全
     */
    static class MyThread6 implements Runnable{

        private int count = 5;

        @Override
        public void run() {
            synchronized (this){
                System.out.println("name " + Thread.currentThread().getName() + " " + (count--) );
            }
        }
    }


    /**
     * 线程不安全
     */
    static class MyThread61 implements Runnable{

        private int count = 5;

        @Override
        public void run() {
            System.out.println((count--)+ " " + "name " + Thread.currentThread().getName()  );
        }
    }

    public static void main(String[] args) {
        MyThread6 myThread6 = new MyThread6();
        Thread t1 = new Thread(myThread6, "A");
        Thread t2 = new Thread(myThread6, "B");
        Thread t3 = new Thread(myThread6, "C");
        t1.start();
        t2.start();
        t3.start();
        /*
        name A 5
        name C 4
        name B 3
         */

        /**
         * 线程不安全
         */
        MyThread61 myThread61 = new MyThread61();
        Thread t4 = new Thread(myThread61, "A1");
        Thread t5 = new Thread(myThread61, "B1");
        Thread t6 = new Thread(myThread61, "C1");
        Thread t7 = new Thread(myThread61, "D1");
        Thread t8 = new Thread(myThread61, "E1");
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        /* 有可能出现 线程不安全
        5 name B1
        3 name D1
        2 name E1
        4 name C1
        5 name A1
         */
    }
}
