package com.vsu.c1.t3;

/**
 * Created by vsu on 2018/01/24.
 */

/**
 * 新建一个新的线程对象后，再调用它的start方法，系统会为此线程分配CPU资源，使其处于Runnable(可运行)状态，
 *      这是一个准备运行的阶段。如果线程抢占到CPU资源，此线程就处于Running(运行)状态。
 *
 * Runnable状态和Running状态可相互切换，因为有可能线程运行一段时间后，有其他高优先级的线程抢占了CPU资源，
 *      这时就从Running变成Runnable状态的5中情况：
 *          调用sleep方法后经过的时间超过了指定的休眠时间
 *          线程调用的阻塞IO已经返回，阻塞方法执行完毕
 *          线程成功地获得了试图同步的监视器
 *          线程正在等待某个通知，其他线程发出了通知
 *          处于挂起状态的线程调用了resume恢复方法
 *
 * Blocked是阻塞的意思。Blocked状态结束后，进入Runnable状态，等待系统重新分配资源
 *      出现阻塞的5中情况：
 *          线程调用sleep方法，主动放弃占用的处理器资源
 *          线程调用了阻塞式IO方法，在该方法返回前，该线程被阻塞
 *          线程试图获得一个同步监视器，但该同步监视器正被其他线程所持有
 *          线程等待某个通知
 *          线程调用了suspend方法将该线程挂起，此方法容易导致死锁，尽量避免使用
 *
 * run方法运行结束后进入销毁阶段，整个线程执行完毕
 */


/**
 * 1 等待/通知机制的实现
 *
 * 方法wait()的作用是使当前执行的代码的线程进行等待，wait()方法是Object类的方法，该方法用来将当前线程置入
 *      “预执行队列”中，并在wait所在的代码行处停止，直到接到通知或被中断为止。在调用wait方法之前，线程必须
 *      获得该对象的对象级别锁，即只能在同步方法或同步代码块中调用wait方法。
 *      在执行wait方法后，当前线程释放锁。在从wait返回前，线程与其他线程竞争重新获得锁。
 *      如果调用wait时没有持有适当的锁，则抛出IllegalMonitorStateException，它是RuntimeException的一个子类，因此不需要try-catch
 *
 * 方法notify()也要在同步方法或同步代码块中调用。如果调用wait时没有持有适当的锁，则抛出IllegalMonitorStateException。
 *      该方法用来通知那些可能等待该对象的对象锁的其他线程，如果有多个线程等待，则由线程规划器随机挑出其中一个
 *      呈wait状态的线程，对其发出通知notify，并使它等待获取该对象的对象锁。
 *      需要说明的是：在执行notify方法后，当前线程不会马上释放该对象锁，呈wait状态的线程并不能马上获取该对象锁
 *          要等到执行notify方法的线程将程序执行完，也就是退出synchronized代码块后，当前线程才会释放锁，而呈wait状态
 *          所在的线程才可以获取该对象锁。
 *
 */
class MyThread1a extends Thread{
    private Object lock = null;

    public MyThread1a(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run(){
        try {
            synchronized (lock) {
                System.out.println("开始 " + System.currentTimeMillis());
                lock.wait();
                System.out.println("结束 " + System.currentTimeMillis());
            }
        }catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}


class MyThread1b extends Thread{
    private Object lock = null;

    public MyThread1b(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run(){
        synchronized (lock) {
            System.out.println("开始 " + System.currentTimeMillis());
            lock.notify();
            System.out.println("结束 " + System.currentTimeMillis());
        }
    }
}


/**
 * 2 notifyAll()方法可以使所有正在等待队列中等待同一共享资源的“全部”线程从等待状态退出，进入可运行状态。
 *      此时，优先级最高的那个线程最先执行，但也有可能是随机执行，因为要取决于JVM虚拟机的实现
 *
 */

public class Test1 {
    public static void main(String[] args) {
        /**
         * 1
         */
        try {
            Object lock = new Object();
            MyThread1a myThread1a = new MyThread1a(lock);
            myThread1a.start();
            Thread.sleep(3000);

            MyThread1b myThread1b = new MyThread1b(lock);
            myThread1b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        开始 1516763144870
        开始 1516763147871
        结束 1516763147871
        结束 1516763147871
         */
    }

}
