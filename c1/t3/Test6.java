package com.vsu.c1.t3;

/**
 * Created by vsu on 2018/01/26.
 */


/**
 * 类InheritableThreadLocal的使用
 * 可以在子线程中取得父线程继承下来的值
 */


import java.util.Date;

/**
 * 值继承
 * 值继承后修改
 */
class InheritableThreadLocalExt extends  InheritableThreadLocal{
    @Override
    protected Object initialValue(){
        return new Date().getTime();
    }

    /**
     * 修改
     */
    @Override
    protected Object childValue(Object parentValue){
        return parentValue + "在子线程加的";
    }
}

class Tools6a{
    public static InheritableThreadLocalExt t1 = new InheritableThreadLocalExt();
}

class Thread6a extends Thread{
    @Override
    public void run(){
        try {
            for (int i=0; i<100; i++){
                System.out.println("在Thread6a线程中取值 =  " + Tools6a.t1.get());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}


public class Test6 {
    public static void main(String[] args) {
        try {
            for (int i=0; i<100; i++){
                System.out.println("在Main线程中取值 =  " + Tools6a.t1.get());
                Thread.sleep(100);
                Thread6a thread6a = new Thread6a();
                thread6a.start();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        在Main线程中取值 =  1516938642325
        在Thread6a线程中取值 =  1516938642325
        在Main线程中取值 =  1516938642325
        在Thread6a线程中取值 =  1516938642325
         */

        /*修改 ：
        在Main线程中取值 =  1516938800395
        在Thread6a线程中取值 =  1516938800395在子线程加的
        在Thread6a线程中取值 =  1516938800395在子线程加的
        在Main线程中取值 =  1516938800395
        在Thread6a线程中取值 =  1516938800395在子线程加的
         */
    }
}
