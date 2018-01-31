package com.vsu.c1.t2;

/**
 * Created by vsu on 2017/12/24.
 */


/** 2.2.7 将任意对象作为对象监视器
 *  多个线程调用同一个对象中的不同名称的synchronized同步方法或synchronized(this)同步代码块时，
 *  调用的效果就是按顺序执行，也就是同步的，阻塞的。
 *  这说明synchronized同步方法或synchronized(this)同步代码块分别有两种作用。
 *
 *  synchronized同步方法：
 *  1 对其他synchronized同步方法或synchronized(this)同步代码块调用呈阻塞状态
 *  2 同一时间只有一个线程可以执行synchronized同步方法中的代码
 *
 *   synchronized(this)同步代码块：
 *  1 对其他synchronized同步方法或synchronized(this)同步代码块调用呈阻塞状态
 *  2 同一时间只有一个线程可以执行synchronized(this)同步代码块中的代码
 *
 *
 *  synchronized(非this对象x)同步代码块：
 *  1 在多个线程持有“对象监视器”为同一个对象的前提下，同一时间只有一个线程可以执行synchronized(非this对象x)同步代码块的代码
 *  2 当持有“对象监视器”为同一个对象的前提下，同一时间只有一个线程可以执行synchronized(非this对象x)同步代码块的代码
 *
 * 锁定非this对象具有一定的优点：如果在一个类中有很多个synchronized方法，这时虽然能实现同步，但会受到阻塞。
 * 但如果使用同步代码块锁定非this对象，则synchronized(非this对象x)代码块中的程序与同步方法是异步的。
 */


/**
 * 验证synchronized(非this对象x)同步代码块的第一点
 */
class Service4{
    private String usernameParam;
    private String passwordParam;
    private String anyString = new String();

    public void setUsernamePassword(String username, String password){
        try {
            synchronized (anyString) {
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入同步代码块");
                usernameParam = username;
                Thread.sleep(3000);
                passwordParam = password;
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开同步代码块");
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}

/**
 *不是同一个对象监视器，异步调用，交叉运行
 */
class Service5{
    private String usernameParam;
    private String passwordParam;

    public void setUsernamePassword(String username, String password){
        try {
            String anyString = new String();//不同对象
            synchronized (anyString) {
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入同步代码块");
                usernameParam = username;
                Thread.sleep(3000);
                passwordParam = password;
                System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开同步代码块");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadA4 extends Thread{
    private Service4 service = null;

    public ThreadA4(Service4 service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.setUsernamePassword("a","aa");
    }
}


class ThreadB4 extends Thread{
    private Service4 service = null;

    public ThreadB4(Service4 service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.setUsernamePassword("b","bb");
    }
}


public class Test4 {
    public static void main(String[] args) {
        Service4 service4 = new Service4();
        ThreadA4 threadA4 = new ThreadA4(service4);
        threadA4.setName("A");
        threadA4.start();

        ThreadB4 threadB4 = new ThreadB4(service4);
        threadB4.setName("B");
        threadB4.start();

        /*
        线程名称为：A 在 1514085482737 进入同步代码块
        线程名称为：A 在 1514085485751 离开同步代码块
        线程名称为：B 在 1514085485751 进入同步代码块
        线程名称为：B 在 1514085488758 离开同步代码块
         */
    }
}
