package com.vsu.c1.t2;

/**
 * Created by vsu on 2018/01/22.
 */


/**
 * 同步synchronized方法无线等待与解决
 */


/**
 * 1 同步方法容易造成死循环
 */
class Service8a{
    synchronized public void methodA(){
        System.out.println("a menthodA begin");
        boolean isContinueRun = true;
        while(isContinueRun){
        }
        System.out.println("a methodA end");
    }

    synchronized public void methodB(){
        System.out.println("a methodB begin");
        System.out.println("a methodB end");
    }
}


class Thread8a1 extends Thread{
    private Service8a service= null;

    public Thread8a1(Service8a service){
        super();
        this.service = service;
    }
    @Override
    public void run(){
        service.methodA();
    }
}

class Thread8a2 extends Thread{
    private Service8a service= null;

    public Thread8a2(Service8a service){
        super();
        this.service = service;
    }
    @Override
    public void run(){
        service.methodB();
    }
}


/**
 * 2 解决
 */
class Service8b{
    Object object1 = new Object();
    Object object2 = new Object();

    public void methodA(){
        synchronized (object1){
            System.out.println("a menthodA begin");
            boolean isContinueRun = true;
            while(isContinueRun){
            }
            System.out.println("a methodA end");
        }
    }

    public void methodB(){
        synchronized (object2){
            System.out.println("a methodB begin");
            System.out.println("a methodB end");
        }

    }
}


class Thread8b1 extends Thread{
    private Service8b service= null;

    public Thread8b1(Service8b service){
        super();
        this.service = service;
    }
    @Override
    public void run(){
        service.methodA();
    }
}

class Thread8b2 extends Thread{
    private Service8b service= null;

    public Thread8b2(Service8b service){
        super();
        this.service = service;
    }
    @Override
    public void run(){
        service.methodB();
    }
}

public class Test8 {
    public static void main(String[] args) {
        /**
         * 1
         */
        Service8a servicea = new Service8a();
        Thread8a1 thread8a1 = new Thread8a1(servicea);
        thread8a1.start();

        Thread8a2 thread8a2 = new Thread8a2(servicea);
        thread8a2.start();
        /* 死锁，b得不到执行的机会
        a menthodA begin
         */


        /**
         * 2
         */
        Service8b serviceb = new Service8b();
        Thread8b1 thread8b1 = new Thread8b1(serviceb);
        thread8b1.start();

        Thread8b2 thread8b2 = new Thread8b2(serviceb);
        thread8b2.start();
        /*解决：
        a menthodA begin
        a methodB begin
        a methodB end
         */

    }

}
