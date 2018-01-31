package com.vsu.c1.t1;

/**
 * Created by vsu on 2017/11/29.
 */


/**
 *   this.interrupted() 测试当前线程是否已经中断  有清除状态的能力 连续两次调用，第二次返回false
 *   this.isInterrupted() 测试线程Thread对象是否已经中断 不清除状态
 */





public class TestInterrupt {

    static class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i=0; i<50000; i++){

                System.out.println("i =  " + (i+1));
            }

        }
    }


    public static void main(String[] args) {
        /**
         * this.interrupted()
         */
//        test1();

        /**
         * this.isInterrupted()
         */
        test2();

    }

    /**
     *  interrupted()
     */
    public static void test1(){

        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(1000);
            myThread.interrupt();
            System.out.println("是否停止1 " + myThread.interrupted());
            System.out.println("是否停止2 " + myThread.interrupted());

            /*
            i =  49995
            i =  49996
            i =  49997
            i =  49998
            i =  49999
            i =  50000
            是否停止1 false
            是否停止2 false
             */
            /**
             *  输出 false 说明测试的是当前线程 main
             *
             *  中断main
             *      Thread.currentThread().interrupt();
             *      Thread.interrupted();
             */

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * isInterrupted()
     */
    public static void test2(){

        try {
            MyThread myThread1 = new MyThread();
            myThread1.start();
            Thread.sleep(1000); // ---
            myThread1.interrupt();
            System.out.println("是否停止1 " + myThread1.isInterrupted());

            System.out.println("是否停止2 " + myThread1.isInterrupted());


        } catch (InterruptedException e) {
            System.out.println("aaa");
            e.printStackTrace();
        }

        System.out.println("end");

    }
}
