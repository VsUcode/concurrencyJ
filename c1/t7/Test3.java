package com.vsu.c1.t7;

/** SimpleDateFormat非线程安全
 * Created by vsu on 2018/01/31.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 1
 * 解决异常方法1
 */
class MyThreadA extends Thread{
    private SimpleDateFormat sdf = null;
    private String dateString = null;

    public MyThreadA(SimpleDateFormat sdf, String dateString){
        super();
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run(){
        try{
            Date date = DateToolsA.parse("yyyy-MM-dd", dateString);
            String newDateString = DateToolsA.format("yyyy-MM-dd", date).toString();
            System.out.println(dateString + " " + newDateString);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}

class DateToolsA{
    public static Date parse(String format, String dateString) throws ParseException {
        return new SimpleDateFormat(format).parse(dateString);
    }

    public static String format(String format, Date date){
        return new SimpleDateFormat(format).format(date).toString();
    }
}


/**
 * 2
 * 解决异常方法2
 */
class MyThreadB extends Thread{
    private SimpleDateFormat sdf = null;
    private String dateString = null;

    public MyThreadB(SimpleDateFormat sdf, String dateString){
        super();
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run(){
        try{
            Date date = DateToolsB.getSimpleDateFormat("yyyy-MM-dd").parse(dateString);
            String newDateString = DateToolsB.getSimpleDateFormat("yyyy-MM-dd").format(date).toString();
            System.out.println(dateString + " " + newDateString);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}

class DateToolsB{
    private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<>();

    public static SimpleDateFormat getSimpleDateFormat(String date){
        SimpleDateFormat sdf = null;
        sdf = t1.get();
        if(sdf == null){
            sdf = new SimpleDateFormat(date);
            t1.set(sdf);
        }

        return sdf;
    }
}




public class Test3 {
    public static void main(String[] args) {
        /**
         * 1
         */
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String[] dateStringArray = new String[]{
//                "2018-01-28", "2018-01-29", "2018-01-30"};
//        MyThreadA[] threadArray = new MyThreadA[3];
//        for (int i=0; i<3; i++){
//            threadArray[i] = new MyThreadA(sdf, dateStringArray[i]);
//        }
//        for (int i=0; i<3; i++){
//            threadArray[i].start();
//        }
        /*
        2018-01-29 2018-01-29
        2018-01-28 2018-01-28
        2018-01-30 2018-01-30
         */


        /**
         * 2
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray = new String[]{
                "2018-01-28", "2018-01-29", "2018-01-30"};
        MyThreadB[] threadArray = new MyThreadB[3];
        for (int i=0; i<3; i++){
            threadArray[i] = new MyThreadB(sdf, dateStringArray[i]);
        }
        for (int i=0; i<3; i++){
            threadArray[i].start();
        }
        /*
        2018-01-30 2018-01-30
        2018-01-28 2018-01-28
        2018-01-29 2018-01-29

         */
    }
}
