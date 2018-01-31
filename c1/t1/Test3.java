package com.vsu.c1.t1;

/**
 * Created by vsu on 2017/11/28.
 */


/**
 *  不共享数据
 *   每个线程拥有一个count
 */
public class Test3 {

    static class Mythread implements Runnable{

        private int count = 5;

        @Override
        public void run() {
            while (count>0){
                count--;
                System.out.println("线程名" + Thread.currentThread().getName() + " " + count);
            }
        }
    }

    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        Thread t1 = new Thread(mythread,"A");
        Thread t2 = new Thread(mythread, "B");
        Thread t3 = new Thread(mythread);
        t3.setName("C");
        t1.start();
        t2.start();
        t3.start();
        /*
        线程名B 2
        线程名A 2
        线程名C 2
        线程名A 0
        线程名B 1
         */

    }
}
