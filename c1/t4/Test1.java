package com.vsu.c1.t4;

/**Lock的使用
 * Created by vsu on 2018/01/27.
 */


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *1 使用ReenrantLock实现同步：测试1
 * ReentrantLock类在功能上相比synchronized更多
 */
class Service1a{
    private Lock lock = new ReentrantLock();

    public void testMethod1a(){
        lock.lock();
        for (int i=0; i<5; i++){
            System.out.println("name = " +  Thread.currentThread().getName() + (" " + (i +1)));
        }
        lock.unlock();
    }
}

class MyThread1a extends Thread{
    private Service1a service1a = null;

    public MyThread1a(Service1a service1a){
        super();
        this.service1a = service1a;
    }

    @Override
    public void run(){
        service1a.testMethod1a();
    }
}
public class Test1 {
    public static void main(String[] args) {
        /**
         * 1
         */
        t1();
    }

    /**
     * 1
     */
    private static void t1(){
        Service1a service1a = new Service1a();
        MyThread1a m1 = new MyThread1a(service1a);
        MyThread1a m2 = new MyThread1a(service1a);
        MyThread1a m3 = new MyThread1a(service1a);
        MyThread1a m4 = new MyThread1a(service1a);
        m1.start();
        m2.start();
        m3.start();
        m4.start();
        /*当前线程打印完毕之后将锁进行释放
        name = Thread-1 1
        name = Thread-1 2
        name = Thread-1 3
        name = Thread-1 4
        name = Thread-1 5
        name = Thread-2 1
        name = Thread-2 2
        name = Thread-2 3
        name = Thread-2 4
        name = Thread-2 5
        name = Thread-0 1
        name = Thread-0 2
        name = Thread-0 3

         */

    }

}
