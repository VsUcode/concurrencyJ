package com.vsu.c1.t4;

/**公平锁与非公平锁
 * Created by vsu on 2018/01/28.
 */


import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock分公平锁和非公平锁
 * 公平锁：线程获得锁的顺序使按照线程加锁的顺序来分配的，FIFO
 * 非公平锁：一种获取锁的抢占机制
 */
class Service5a{
    private ReentrantLock reentrantLock = null;

    public Service5a(boolean isFair){
        super();
        reentrantLock = new ReentrantLock(isFair);
    }

    public void serviceMethod(){
        try{
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
        }finally {
            reentrantLock.unlock();
        }

    }
}


public class Test5 {
    public static void main(String[] args) {
        /**
         * 1
         * 公平锁
         */
//        t1();

        /**
         * 2
         * 非公平锁
         */
        t2();
    }

    /**
     * 1
     * 公平锁
     */
    private static void t1(){
        final Service5a service5a = new Service5a(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 运行了");
                service5a.serviceMethod();
            }
        };
        Thread[] threads = new Thread[10];
        for (int i=0; i<10; i++){
            threads[i]= new Thread(runnable);
        }
        for (int i=0; i<10; i++){
            threads[i].start();
        }
        /*按顺序获得
        Thread-1 运行了
        Thread-0 运行了
        Thread-2 运行了
        Thread-1获得锁
        Thread-4 运行了
        Thread-5 运行了
        Thread-8 运行了
        Thread-3 运行了
        Thread-9 运行了
        Thread-6 运行了
        Thread-7 运行了
        Thread-0获得锁
        Thread-2获得锁
        Thread-4获得锁
        Thread-5获得锁
        Thread-8获得锁
        Thread-3获得锁
        Thread-9获得锁
        Thread-6获得锁
        Thread-7获得锁

         */
    }


    /**
     * 2
     * 非公平锁
     */
    private static void t2() {
        final Service5a service5a = new Service5a(false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 运行了");
                service5a.serviceMethod();
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
        /*乱序
        Thread-1 运行了
        Thread-5 运行了
        Thread-1获得锁
        Thread-5获得锁
        Thread-4 运行了
        Thread-4获得锁
        Thread-0 运行了
        Thread-0获得锁
        Thread-8 运行了
        Thread-8获得锁
        Thread-3 运行了

         */
    }

}
