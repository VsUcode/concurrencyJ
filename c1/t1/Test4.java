package com.vsu.c1.t1;

/**
 * Created by vsu on 2017/11/28.
 */


/**
 *  共享数据
 */
public class Test4 {

    static class MyThread4 implements Runnable{

        private int count = 5;

        @Override
        public void run() {
            count--;
            System.out.println("name " + Thread.currentThread().getName() + " " + count);
        }
    }

    public static void main(String[] args) {
        MyThread4 myThread4 = new MyThread4();
        Thread t1 = new Thread(myThread4,"A");
        Thread t2 = new Thread(myThread4, "B");
        Thread t3 = new Thread(myThread4, "C");
        t1.start();
        t2.start();
        t3.start();
        /*
        name A 2
        name B 2
        name C 2
         */
    }
}
