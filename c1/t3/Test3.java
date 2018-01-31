package com.vsu.c1.t3;

/**
 * Created by vsu on 2018/01/25.
 */


/**
 * 等待/通知模式最经典的案例就是 生产者/消费者模式
 */


import com.sun.beans.decoder.ValueObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 1
 * 一生产与一消费：操作值
 */
class P1{

    private String lock = null;

    public P1(String lock){
        super();
        this.lock = lock;
    }

    public void setValue(){

        try {
            synchronized (lock) {
                if (!ValueObject1.value.equals("")) {
                    lock.wait();
                }
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                System.out.println("set value ：" + value);
                ValueObject1.value = value;
                lock.notify();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class C1{

    private String lock = null;

    public C1(String lock){
        super();
        this.lock = lock;
    }

    public void getValue(){

        try {
            synchronized (lock) {
                if (ValueObject1.value.equals("")) {
                    lock.wait();
                }
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                System.out.println("get value ：" + value);
                ValueObject1.value = "";
                lock.notify();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class ValueObject1{
    public static String value = "";
}

class Thread3a extends Thread{
    private P1 p = null;

    public Thread3a(P1 p){
        super();
        this.p = p;
    }

    @Override
    public void run(){
        while(true){
            p.setValue();
        }
    }
}


class Thread3b extends Thread{
    private C1 c = null;

    public Thread3b(C1 c){
        super();
        this.c = c;
    }

    @Override
    public void run(){
        while(true){
            c.getValue();
        }
    }
}


/**
 * 2
 * 多生产者与多消费者：操作值-假死
 * 假死现象其实就是线程进入waiting等待状态。如果全部线程都进入waiting状态，则程序就不再执行任何业务功能了
 * 解决假死很简单，将notify方法改成notifyAll方法
 */


/**
 * 3
 * 一生产与一消费：操作栈
 * 使生产者向堆栈List对象中放入数据，使消费者从List堆栈中取出数据。
 * List最大容量是1，实验环境只有一个生产者与一个消费者
 */
class MyStack3a{
    private List list1 = new ArrayList();

    synchronized public void push(){

        try {
            if (list1.size() == 1){
                this.wait();
            }
            list1.add("anyString = " + Math.random());
            this.notify();
            System.out.println("push = " + list1.size());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public String pop(){
        String returnValue = "";

        try {
            if (list1.size() == 0){
                System.out.println("pop操作中的："  + Thread.currentThread().getName() + " 线程呈wait状态");
                this.wait();
            }
            returnValue = "" + list1.get(0);
            list1.remove(0);
            this.notify();
            System.out.println("pop = " + list1.size());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

}

class P2{
    private  MyStack3a myStack3a = null;

    public P2(MyStack3a myStack3a){
        super();
        this.myStack3a = myStack3a;
    }

    public void pushService(){
        myStack3a.push();
    }
}


class C2{
    private  MyStack3a myStack3a = null;

    public C2(MyStack3a myStack3a){
        super();
        this.myStack3a = myStack3a;
    }

    public void popService(){
        System.out.println("pop = " + myStack3a.pop());
    }
}

class Thread3c extends Thread{
    private P2 p = null;

    public Thread3c(P2 p){
        super();
        this.p = p;
    }

    @Override
    public void run(){
        while(true){
            p.pushService();
        }
    }
}

class Thread3d extends Thread{
    private C2 c = null;

    public Thread3d(C2 c){
        super();
        this.c = c;
    }

    @Override
    public void run(){
        while(true){
            c.popService();
        }
    }
}


/**
 * 4
 * 一生产与多消费：操作栈，解决wait条件改变与假死
 * List最大容量还是1
 */
class MyStack3b{
    private List list1 = new ArrayList();

    synchronized public void push(){

        try {
            while (list1.size() == 1){//////////////////更改为while
                this.wait();
            }
            list1.add("anyString = " + Math.random());
            this.notifyAll();////////////////////避免假死
            System.out.println("push = " + list1.size());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public String pop(){
        String returnValue = "";

        try {
            while (list1.size() == 0){///////////更改为while
                System.out.println("pop操作中的："  + Thread.currentThread().getName() + " 线程呈wait状态");
                this.wait();
            }
            returnValue = "" + list1.get(0);
            list1.remove(0);
            this.notifyAll();///////////////////////避免假死
            System.out.println("pop = " + list1.size());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

}


class P3{
    private  MyStack3b myStack3b = null;

    public P3(MyStack3b myStack3b){
        super();
        this.myStack3b = myStack3b;
    }

    public void pushService(){
        myStack3b.push();
    }
}


class C3{
    private  MyStack3b myStack3b = null;

    public C3(MyStack3b myStack3b){
        super();
        this.myStack3b = myStack3b;
    }

    public void popService(){
        System.out.println("pop = " + myStack3b.pop());
    }
}

class Thread3e extends Thread{
    private P3 p = null;

    public Thread3e(P3 p){
        super();
        this.p = p;
    }

    @Override
    public void run(){
        while(true){
            p.pushService();
        }
    }
}

class Thread3f extends Thread{
    private C3 c = null;

    public Thread3f(C3 c){
        super();
        this.c = c;
    }

    @Override
    public void run(){
        while(true){
            c.popService();
        }
    }
}


/**
 * 5
 * 多生产与一消费：操作栈
 * List最大容量还是1
 */


/**
 * 6
 * 多生产与多消费：操作栈
 * List容量还是1
 */

public class Test3 {
    public static void main(String[] args) {
        /**
         * 1
         */
//        t1();

        /**
         * 3
         */
//        t3();


        /**
         * 4
         */
        t4();
    }

    /**
     * 1
     */
    private static void t1(){
        String lock = new String("");
        P1 p = new P1(lock);
        C1 c = new C1(lock);
        Thread3a thread3a = new Thread3a(p);
        Thread3b thread3b = new Thread3b(c);
        thread3a.start();
        thread3b.start();
        /*
        set value ：1516854142697_12520339751126
        get value ：1516854142697_12520339759337
        set value ：1516854142697_12520339768780
        get value ：1516854142697_12520339776580
        set value ：1516854142697_12520339786023
        get value ：1516854142697_12520339793823

         */
    }


    /**
     * 3
     */
    private static void t3(){
        MyStack3a myStack3a = new MyStack3a();
        P2 p = new P2(myStack3a);
        C2 c = new C2(myStack3a);
        Thread3c thread3c = new Thread3c(p);
        Thread3d thread3d = new Thread3d(c);
        thread3c.start();
        thread3d.start();
        /*
        push = 1
        pop = 0
        pop = anyString = 0.12321226572221011
        push = 1
        pop = 0
        pop = anyString = 0.08206939291184168
         */
    }


    /**
     * 4
     */
    private static void t4(){
        MyStack3b myStack3b = new MyStack3b();
        P3 p = new P3(myStack3b);
        C3 c1 = new C3(myStack3b);
        C3 c2 = new C3(myStack3b);
        C3 c3 = new C3(myStack3b);
        C3 c4 = new C3(myStack3b);
        C3 c5 = new C3(myStack3b);
        Thread3e thread3e = new Thread3e(p);
        thread3e.start();
        Thread3f t1 = new Thread3f(c1);
        Thread3f t2 = new Thread3f(c2);
        Thread3f t3 = new Thread3f(c3);
        Thread3f t4 = new Thread3f(c4);
        Thread3f t5 = new Thread3f(c5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        /*
        pop = anyString = 0.785706427331104
        pop操作中的：Thread-4 线程呈wait状态
        pop操作中的：Thread-5 线程呈wait状态
        push = 1
        pop = 0
        pop = anyString = 0.6484674482375411
        pop操作中的：Thread-3 线程呈wait状态
        pop操作中的：Thread-1 线程呈wait状态
        pop操作中的：Thread-2 线程呈wait状态
         */
    }


}
