package com.vsu.c1.t1;

/**
 * Created by vsu on 2017/11/29.
 */


/**
 *  this 和 Thread.currentTread
 */
public class TestIsAlive {

    static class Test implements Runnable{

        public Test(){
            System.out.println("test begin");
            System.out.println("Thread.currentThread().getName()  " + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive()  " + Thread.currentThread().isAlive());
            System.out.println("test end");

        }


        @Override
        public void run() {
            System.out.println("run begin");
            System.out.println("Thread.currentThread().getName()  " + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive()  " + Thread.currentThread().isAlive());
            System.out.println("run end");
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        Thread t1 = new Thread(test);
        System.out.println();
        t1.start();
    }

}
