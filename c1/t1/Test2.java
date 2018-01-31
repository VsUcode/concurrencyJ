package com.vsu.c1.t1;

/**
 * Created by vsu on 2017/11/28.
 */


/**
 * Runnable
 */
public class Test2 {

    static class MyRunable implements Runnable{

        @Override
        public void run() {
            System.out.println("Test2 Runable");
        }
    }

    public static void main(String[] args) {
//        Runnable runnable = new MyRunable();
        Thread t1 = new Thread(new MyRunable());
        t1.start();
        System.out.println("test");
    }
}
