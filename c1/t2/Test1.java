package com.vsu.c1.t2;

/** 2.2.1 synchronized方法的弊端
 * Created by vsu on 2017/12/23.
 */

class Task{
    private String data1;
    private String data2;

    public synchronized void doLongTimeTask(){
        try {
            System.out.println("begin task");
            Thread.sleep(3000);
            data1 = "长时间处理任务后从远程返回的值1 threadName= " + Thread.currentThread().getName();
            data2 = "长时间处理任务后从远程返回的值2 threadName= " + Thread.currentThread().getName();
            System.out.println(data1);
            System.out.println(data2);
            System.out.println("end task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class CommonUtils{
    public static long beginTime1;
    public static long endTime1;
    public static long beginTime2;
    public static long endTime2;
}

class MyThread1 extends Thread{
    private Task task;

    public MyThread1(Task task){
        super();
        this.task = task;
    }
    @Override
    public void run(){
        super.run();
        CommonUtils.beginTime1 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime1 = System.currentTimeMillis();
    }
}

class MyThread2 extends Thread{
    private Task task;

    public MyThread2(Task task){
        super();
        this.task = task;
    }
    @Override
    public void run(){
        super.run();
        CommonUtils.beginTime2 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime2 = System.currentTimeMillis();
    }
}

public class Test1 {
    public static void main(String[] args) {
        Task task = new Task();
        MyThread1 myThread1 = new MyThread1(task);
        myThread1.start();
        MyThread2 myThread2 = new MyThread2(task);
        myThread2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long beginTime = CommonUtils.beginTime1;
        if (CommonUtils.beginTime2 < CommonUtils.beginTime1){
            beginTime = CommonUtils.beginTime2;
        }
        long endTime = CommonUtils.endTime1;
        if (CommonUtils.endTime1 < CommonUtils.endTime2){
            endTime = CommonUtils.endTime2;
        }

        System.out.println("耗时：" + ((endTime - beginTime)/1000));
        /* 耗时太长--使用同步代码块
            begin task
            长时间处理任务后从远程返回的值1 threadName= Thread-0
            长时间处理任务后从远程返回的值2 threadName= Thread-0
            end task
            begin task
            长时间处理任务后从远程返回的值1 threadName= Thread-1
            长时间处理任务后从远程返回的值2 threadName= Thread-1
            end task
            耗时：6
         */
    }
}
