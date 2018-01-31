package com.vsu.c1.t2;

/**
 * Created by vsu on 2018/01/22.
 */


/**
 * 数据类型string的常量池特性
 * 在JVM中具有string常量池缓存的功能
 * 大多数情况下，不使用string锁
 */
class Service7{
    public static void print(String param){
        try {
            synchronized (param) {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        }catch (InterruptedException e) {
                    e.printStackTrace();
        }
    }
}

class Thread7a1 extends Thread{
    private Service7 service7= null;

    public Thread7a1(Service7 service7){
        super();
        this.service7 = service7;
    }
    @Override
    public void run(){
        service7.print("aa");
    }
}

class Thread7a2 extends Thread{
    private Service7 service7= null;

    public Thread7a2(Service7 service7){
        super();
        this.service7 = service7;
    }
    @Override
    public void run(){
        service7.print("aa");
    }
}


public class Test7 {
    public static void main(String[] args) {
        Service7 service = new Service7();
        Thread7a1 thread7a1 = new Thread7a1(service);
        thread7a1.setName("a");
        thread7a1.start();

        Thread7a2 thread7a2 = new Thread7a2(service);
        thread7a2.setName("b");
        thread7a2.start();;
    }
    /* 线程b未执行：
    a
    a
    a
    a
    a
    a
    a
     */

}
