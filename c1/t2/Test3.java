package com.vsu.c1.t2;

/** 2.2.5 synchronized代码块间的同步性
 * Created by vsu on 2017/12/23.
 *
 * 在使用同步synchronized(this)代码块时需要注意的是，当一个线程访问object的一个synchronized(this)同步代码块时，
 * 其他线程对同一个object中所有其他synchronized(this)同步代码块的访问将被阻塞，这说明synchronized使用的“对象监视器”是一个
 */

class ObjectService{
    public void serviceMethodA(){
        synchronized(this){
            try {
                System.out.println("A begin time = " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("A end   time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void serviceMethodB(){
        synchronized(this){
                System.out.println("B begin time = " + System.currentTimeMillis());
                System.out.println("B end   time = " + System.currentTimeMillis());
        }
    }
}


class MyThreadA extends Thread{
    private ObjectService service;

    public MyThreadA(ObjectService service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        super.run();
        service.serviceMethodA();
    }
}


class MyThreadB extends Thread{
    private ObjectService service;

    public MyThreadB(ObjectService service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        super.run();
        service.serviceMethodB();
    }
}




public class Test3 {
    public static void main(String[] args) {
        ObjectService objectService = new ObjectService();
        MyThreadA ta = new MyThreadA(objectService);
        ta.setName("a");
        ta.start();

        MyThreadB tb = new MyThreadB(objectService);
        tb.setName("b");
        tb.start();
        /* B在A释放锁之后执行
        A begin time = 1513998745734
        A end   time = 1513998747736
        B begin time = 1513998747736
        B end   time = 1513998747736
         */
    }
}
