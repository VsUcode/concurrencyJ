package com.vsu.c1.t2;

/**
 * Created by vsu on 2018/01/23.
 */


/**
 * 关键字volatile的主要作用是使变量在多个线程间可见
 *  作用是：强制从公共堆栈中取得变量的值，而不是从线程私有数据中取得
 */


/**
 * 1 关键字volatile解决同步死循环
 */
class PrintString implements Runnable{

    volatile private boolean isContinuePrint = true;

    public boolean isContinuePrint(){
        return isContinuePrint;
    }
    public void setContinuePrint(boolean isContinuePrint){
        this.isContinuePrint = isContinuePrint;
    }
    public void printStringMehod(){
        while (isContinuePrint){
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        printStringMehod();
    }
}




public class Test9 {
    public static void main(String[] args) {
        /**
         * 1
         */
        PrintString printString = new PrintString();
        new Thread(printString).start();
        System.out.println("停止 " + Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }

}
