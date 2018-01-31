package com.vsu.c1.t2;


/** 2.2.3 用同步代码块解决同步方法的弊端（Test1)
 * Created by vsu on 2017/12/23.
 */

class Task1{
    private String data1;
    private String data2;

    public void doLongTimeTask(){
        try {
            System.out.println("begin task");
            Thread.sleep(3000);
            String privateData1 = "长时间处理任务后从远程返回的值1 threadName= " + Thread.currentThread().getName();
            String privateData2 = "长时间处理任务后从远程返回的值2 threadName= " + Thread.currentThread().getName();

            synchronized (this){
                data1 = privateData1;
                data2 = privateData2;
            }
            System.out.println(data1);
            System.out.println(data2);
            System.out.println("end task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class CommonUtils1{
    public static long beginTime1;
    public static long endTime1;
    public static long beginTime2;
    public static long endTime2;
}

class MyThread11 extends Thread{
    private Task1 task;

    public MyThread11(Task1 task){
        super();
        this.task = task;
    }
    @Override
    public void run(){
        super.run();
        CommonUtils1.beginTime1 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils1.endTime1 = System.currentTimeMillis();
    }
}

class MyThread21 extends Thread{
    private Task1 task;

    public MyThread21(Task1 task){
        super();
        this.task = task;
    }
    @Override
    public void run(){
        super.run();
        CommonUtils1.beginTime2 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils1.endTime2 = System.currentTimeMillis();
    }
}

public class Test2 {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        MyThread11 myThread1 = new MyThread11(task1);
        myThread1.start();
        MyThread21 myThread2 = new MyThread21(task1);
        myThread2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long beginTime = CommonUtils1.beginTime1;
        if (CommonUtils1.beginTime2 < CommonUtils1.beginTime1){
            beginTime = CommonUtils1.beginTime2;
        }
        long endTime = CommonUtils1.endTime1;
        if (CommonUtils1.endTime1 < CommonUtils1.endTime2){
            endTime = CommonUtils1.endTime2;
        }

        System.out.println("耗时：" + ((endTime - beginTime)/1000));
        /*
            begin task
            begin task
            长时间处理任务后从远程返回的值1 threadName= Thread-0
            长时间处理任务后从远程返回的值2 threadName= Thread-1
            end task
            长时间处理任务后从远程返回的值1 threadName= Thread-1
            长时间处理任务后从远程返回的值2 threadName= Thread-1
            end task
            耗时：3
         */
    }
}

