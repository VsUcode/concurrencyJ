package com.vsu.c1.t7;

/**
 * Created by vsu on 2018/01/31.
 */

/**
 * 线程的状态：
 * 线程对象在不同的运行时期有不同的状态，状态信息就存在于state枚举类中
 * 调用与线程有关的方法是造成线程状态改变的主要原因
 *
 *  NEW：是线程实例化后还从未执行start方法时的状态
 *  RUNNABLE：线程进入运行状态
 *  TERMINATED：线程被销毁时的状态
 *  TIMED_WAITING：线程执行了Thread.sleep方法，呈等待状态
 *  BLOCKED：某一个线程在等待锁的时候
 *  WAITING：是线程执行了Object.wait方法后所处的状态
 */
public class Test1 {
}
