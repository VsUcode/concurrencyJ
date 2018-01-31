package com.vsu.c1.t5;

/** 定时器Timer的使用
 * Timer类的主要作用就是设置计划任务，但封装任务类却是TimerTask类【抽象类】
 * Created by vsu on 2018/01/29.
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 方法schedule(TimerTask task, Date time)的测试：
 * 该方法的作用是在指定的日期执行一次某一任务
 *
 * 方法schedule(TimerTask task, Date time, long period)的测试：
 * 该方法的作用是在指定的日期之后，按指定的间隔周期性地无限循环地执行某一任务
 *
 * TimerTask类的cancel()方法：
 * 将自身从任务队列中清除
 *
 * Timer类的cancel()方法：
 * 将任务队列中的全部任务清空
 * 注意事项：有时并不一定会停止执行计划任务，而是正常执行。没有争抢到queue锁
 *
 *
 * 方法schedule(TimerTask task, long delay)的测试：
 * 该方法的作用是延迟执行
 *
 * 方法schedule(TimerTask task, long delay, long period)的测试：
 */



public class Test1 {

//    private static Timer timer = new Timer(true);//true：守护线程  1
    private static Timer timer = new Timer();//  2

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 1
     * 执行任务的时间早于/晚于当前时间：在未来执行的效果
     */
    public static class MyTask1 extends TimerTask{

        @Override
        public void run() {
            System.out.println("运行了，时间为：" + sdf.format(new Date()));
        }
    }

    public static void t1(){
        try {
            MyTask1 task1 = new MyTask1();
            String dateString = "2018-01-29 10:57:00";
            Date dateRef = sdf.parse(dateString);
            System.out.println("字符串时间：" + sdf.format(dateRef) + " 当前时间：" + sdf.format(new Date()));
            timer.schedule(task1, dateRef);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
        字符串时间：2018-01-29 10:57:00 当前时间：2018-01-29 11:03:37
        运行了，时间为：2018-01-29 11:03:37
         */
    }


    /**
     * 2
     * 多个TimerTask任务及延时的测试
     * TimerTask是以队列的方式一个一个被顺序执行的，所以执行的时间有可能和预期的时间不一致，延迟
     */
    public static class MyTask2a extends TimerTask{

        @Override
        public void run() {
            try {
                System.out.println("1 开始运行了，时间为：" + sdf.format(new Date()));
                Thread.sleep(20000);
                System.out.println("1 结束运行了，时间为：" + sdf.format(new Date()));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static class MyTask2b extends TimerTask{

        @Override
        public void run() {
            System.out.println("2 开始运行了，时间为：" + sdf.format(new Date()));
            System.out.println("运行了，时间为：" + sdf.format(new Date()));
            System.out.println("2 结束运行了，时间为：" + sdf.format(new Date()));
        }
    }

    public static void t2(){
        try {
            MyTask2a task1 = new MyTask2a();
            MyTask2b task2 = new MyTask2b();
            String dateString1 = "2018-01-29 11:19:00";
            String dateString2 = "2018-01-29 11:19:10";
            Date dateRef1 = sdf.parse(dateString1);
            Date dateRef2 = sdf.parse(dateString2);
            System.out.println("字符串时间：" + sdf.format(dateRef1) + " 当前时间：" + sdf.format(new Date()));
            System.out.println("字符串时间：" + sdf.format(dateRef2) + " 当前时间：" + sdf.format(new Date()));
            timer.schedule(task1, dateRef1);
            timer.schedule(task2, dateRef2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*延迟：
        字符串时间：2018-01-29 11:19:00 当前时间：2018-01-29 11:18:40
        字符串时间：2018-01-29 11:19:10 当前时间：2018-01-29 11:18:40
        1 开始运行了，时间为：2018-01-29 11:19:00
        1 结束运行了，时间为：2018-01-29 11:19:20
        2 开始运行了，时间为：2018-01-29 11:19:20
        运行了，时间为：2018-01-29 11:19:20
        2 结束运行了，时间为：2018-01-29 11:19:20
         */

    }


    public static void main(String[] args) {
//        t1();
        t2();
    }

}
