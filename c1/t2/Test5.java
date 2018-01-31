package com.vsu.c1.t2;

/**
 * Created by vsu on 2018/01/20.
 */


/**
 * 细化验证3个结论：
 *  synchronized(非this对象x)格式的写法是将对象本身作为“对象监视器”，这样就可以得出三个结论：
 *  1 当多个线程同时执行synchronized(x){}同步代码块时呈同步效果
 *  2 当其他线程执行x对象中synchronized同步方法时呈同步效果
 *  3 当其他线程执行x对象方法里面的synchronized(this)代码块时也呈现同步效果
 *
 *  但需要注意的是：如果其他线程调用不加synchronized关键字的方法时，还是异步调用
 *
 */


/**
 * 验证1：
 *  当多个线程同时执行synchronized(x){}同步代码块时呈同步效果
 */
class MyObject{}

class Service5a{
    public void testMethod1(MyObject myObject){
        synchronized(myObject){
            try {
                System.out.println( "线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入同步代码块");
                Thread.sleep(2000);
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开同步代码块");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Thread5a1 extends Thread {
    private Service5a service = null;
    private MyObject myObject = null;

    public Thread5a1(Service5a service, MyObject myObject) {
        super();
        this.service = service;
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        service.testMethod1(myObject);
    }
}



class Thread5a2 extends Thread {
    private Service5a service = null;
    private MyObject myObject = null;

    public Thread5a2(Service5a service, MyObject myObject) {
        super();
        this.service = service;
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        service.testMethod1(myObject);
    }
}


/**
 * 验证2
 *  当其他线程执行x对象中synchronized同步方法时呈现同步效果
 */
class MyObject1{
    synchronized public void speedPrintString(){
        System.out.println( "线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入同步方法");
        System.out.println("--------------------------------------");
        System.out.println( "线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开同步方法");
    }
}

class Service5b{
    public void testMethod2(MyObject1 myObject1){
        synchronized (myObject1){
            try {
                System.out.println( "线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入同步代码块");
                Thread.sleep(5000);
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开同步代码块");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Thread5b1 extends Thread {
    private Service5b service = null;
    private MyObject1 myObject = null;

    public Thread5b1(Service5b service, MyObject1 myObject) {
        super();
        this.service = service;
        this.myObject = myObject;
    }

    @Override
    public void run(){
        super.run();
        service.testMethod2(myObject);
    }
}
class Thread5b2 extends Thread {
    private MyObject1 myObject = null;

    public Thread5b2( MyObject1 myObject) {
        super();
        this.myObject = myObject;
    }

    @Override
    public void run(){
        super.run();
        myObject.speedPrintString();
    }
}


/**
 * 验证3
 *  当其他线程执行x对象方法里面的synchronized(this)代码块时也呈现同步
 */
class MyObject2{
    public void speedPrintString(){
        synchronized (this){
            System.out.println( "线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入同步方法");
            System.out.println("--------------------------------------");
            System.out.println( "线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开同步方法");
        }

    }
}
class Service5c{
    public void testMethod2(MyObject2 myObject2){
        synchronized (myObject2){
            try {
                System.out.println( "线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入同步代码块");
                Thread.sleep(5000);
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开同步代码块");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Thread5c1 extends Thread {
    private Service5c service = null;
    private MyObject2 myObject = null;

    public Thread5c1(Service5c service, MyObject2 myObject) {
        super();
        this.service = service;
        this.myObject = myObject;
    }

    @Override
    public void run(){
        super.run();
        service.testMethod2(myObject);
    }
}
class Thread5c2 extends Thread {
    private MyObject2 myObject = null;

    public Thread5c2( MyObject2 myObject) {
        super();
        this.myObject = myObject;
    }

    @Override
    public void run(){
        super.run();
        myObject.speedPrintString();
    }
}




public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 1
         */
        Service5a service5a = new Service5a();
        MyObject myObject = new MyObject();
        Thread5a1 thread5a1 = new Thread5a1(service5a, myObject);
        thread5a1.setName("thread5a1");
        thread5a1.start();
        Thread5a2 thread5a2 = new Thread5a2(service5a, myObject);
        thread5a2.setName("thread5a2");
        thread5a2.start();
        /* 同步：
        线程名称为：thread5a1 在 1516412437185 进入同步代码块
        线程名称为：thread5a1 在 1516412439186 离开同步代码块
        线程名称为：thread5a2 在 1516412439186 进入同步代码块
        线程名称为：thread5a2 在 1516412441187 离开同步代码块
         */


        /**
         * 2
         */
        Service5b service5b = new Service5b();
        MyObject1 myObject1 = new MyObject1();
        Thread5b1 thread5b1 = new Thread5b1(service5b, myObject1);
        thread5b1.setName("threadb1");
        thread5b1.start();
        Thread.sleep(100);
        Thread5b2 thread5b2 = new Thread5b2(myObject1);
        thread5b2.setName("threadb2");
        thread5b2.start();
        /*同步
        线程名称为：threadb1 在 1516415662497 进入同步代码块
        线程名称为：threadb1 在 1516415667498 离开同步代码块
        线程名称为：threadb2 在 1516415667498 进入同步方法
        --------------------------------------
        线程名称为：threadb2 在 1516415667498 离开同步方法
         */



        /**
         * 3
         */
        Service5c service5c = new Service5c();
        MyObject2 myObject2 = new MyObject2();
        Thread5c1 thread5c1 = new Thread5c1(service5c, myObject2);
        thread5c1.setName("threadc1");
        thread5c1.start();
        Thread.sleep(100);
        Thread5c2 thread5c2 = new Thread5c2(myObject2);
        thread5c2.setName("threadc2");
        thread5c2.start();
        /* 同步：
        线程名称为：threadc1 在 1516416283112 进入同步代码块
        线程名称为：threadc1 在 1516416288114 离开同步代码块
        线程名称为：threadc2 在 1516416288114 进入同步方法
        --------------------------------------
        线程名称为：threadc2 在 1516416288114 离开同步方法
         */

    }

}
