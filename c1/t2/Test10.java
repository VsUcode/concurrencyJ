package com.vsu.c1.t2;

/**
 * Created by vsu on 2018/01/23.
 */


import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile非原子的特性
 *  1 虽然增加了实例变量在多线程之间的可见性，但它却不具备同步性，那么也就不具备原子性
 */
class MyThread10a extends Thread{

    volatile  public static int count;

    private static void addCount(){
        for (int i=0; i<100; i++){
            count++;
        }
        System.out.println("count = " + count);
    }

    @Override
    public void run(){
        addCount();
    }
}


/**
 * 2 改进
 */
class MyThread10b extends Thread{

    volatile  public static int count;

    synchronized private static void addCount(){
        for (int i=0; i<100; i++){
            count++;
        }
        System.out.println("count = " + count);
    }

    @Override
    public void run(){
        addCount();
    }
}


/**
 * 3 使用原子类进行i++操作
 *  除了在i++操作时使用synchroized关键字实现同步外，还可以使用AtomicInteger原子类进行实现
 */
class AddCountThread extends Thread{
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run(){
        for (int i=0; i<10000; i++){
            System.out.println(count.incrementAndGet());
        }
    }
}


/**
 * 4 原子类也不完全安全
 *  原子类在具有逻辑性的情况下输出结果也具有随机性
 */


/**
 * 5 synchroized代码块有volatile同步的功能
 * synchroized可以使多个线程访问同一资源具有同步性，而且它还具有将线程工作内存中的私有变量与公众内存中的变量同步的功能
 * synehroized可以保证在同一时刻，只有一个线程可以执行某一方法或某一个代码块。
 * 它包含两个特征：互斥性和可见性。
 *
 * 学习多线程并发：着重“外练互斥，内修可见”
 */



public class Test10 {
    public static void main(String[] args) {
        /**
         * 1
         */
//        MyThread10a[] myThread10a = new MyThread10a[100];
//        for (int i=0; i<100; i++){
//            myThread10a[i] = new MyThread10a();
//        }
//        for (int i=0; i<100; i++){
//            myThread10a[i].start();
//        }
        /*
        count = 7573
        count = 7779
        count = 7879
        count = 7679
        count = 7679
        count = 7979
        count = 8079

         */




        /**
         * 2
         */
//        MyThread10b[] myThread10b = new MyThread10b[100];
//        for (int i=0; i<100; i++){
//            myThread10b[i] = new MyThread10b();
//        }
//        for (int i=0; i<100; i++){
//            myThread10b[i].start();
//        }
        /*
        count = 9400
        count = 9500
        count = 9600
        count = 9700
        count = 9800
        count = 9900
        count = 10000
         */



        /**
         * 3
         */
        AddCountThread countService = new AddCountThread();
        Thread t1 = new Thread(countService);
        t1.start();
        Thread t2 = new Thread(countService);
        t2.start();
        Thread t3 = new Thread(countService);
        t3.start();
        Thread t4 = new Thread(countService);
        t4.start();
        Thread t5 = new Thread(countService);
        t5.start();
        /*
        49995
        49996
        49997
        49998
        49999
        50000
         */
    }

}
