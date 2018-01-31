package com.vsu.c1.t3;

/**
 * Created by vsu on 2018/01/26.
 */


import java.util.Date;

/**
 * 1
 * 类ThreadLocal的使用：
 *      主要解决的就是每个线程绑定自己的值，可以将ThreadLocal类比喻成全局存放数据的盒子，盒子中可以存储每个线程的私有数据
 *  get()与null
 */
class Test5a{
    public static ThreadLocal t1 = new ThreadLocal();

    public static void main(String[] args) {
        if (t1.get() == null){
            System.out.println("未放过值");
            t1.set("t1值");
        }
        System.out.println(t1.get());

        /*
        未放过值
        t1值
         */
    }
}


/**
 * 2
 * 类ThreadLocal解决的是变量在不同线程间的隔离性
 * 验证线程变量间的隔离性
 * 解决get返回null问题：继承ThreadLocal类赋值
 */
class Tools5a{
    public static ThreadLocalExt t1 = new ThreadLocalExt();
}

class ThreadLocalExt extends ThreadLocal{
    @Override
    protected Object initialValue(){
        return new Date().getTime();
    }
}

class Thread5a extends Thread{
    @Override
    public void run(){
        try {
            for (int i=0; i<100; i++){
            System.out.println("在Thread5a线程中取值 =  " + Tools5a.t1.get());
            Thread.sleep(100);
            }
        } catch (InterruptedException e) {
                e.printStackTrace();

        }
    }
}



public class Test5 {
    public static void main(String[] args) {
        /**
         * 2
         */
        try {
            for (int i=0; i<100; i++){
                System.out.println("在Main线程中取值 =  " + Tools5a.t1.get());
                Thread.sleep(100);
                Thread5a thread5a = new Thread5a();
                thread5a.start();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        在Main线程中取值 =  1516937620558
        在Main线程中取值 =  1516937620558
        在Thread5a线程中取值 =  1516937620659
        在Main线程中取值 =  1516937620558
        在Thread5a线程中取值 =  1516937620758
        在Thread5a线程中取值 =  1516937620659
        在Thread5a线程中取值 =  1516937620758
         */
    }


}
