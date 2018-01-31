package com.vsu.c1.t1;

/**
 * Created by vsu on 2017/11/28.
 */


/**
 *  Thread
 */
public class Test1 {

    class MyThread extends Thread{
        public void run(){
            super.run();
            System.out.println("Test1 ");
        }
    }

    public void main1() {
        Thread myThread = new MyThread();
        myThread.run();
        System.out.println("test");
    }


    public static void main(String[] args) {
        Test1 t = new Test1();
        t.main1();
    }
}
