package com.vsu.c2.part1.t4;

/** 同步工具类；
 *      同步工具类可以是任何一个对象，只要它根据其自身的状态来协调线程的控制流
 *      同步工具类包括：阻塞队列，信号量(Semaphore)、栅栏(Barrier)以及闭锁等
 * Created by vsu on 2018/02/09.
 */


/**
 * 1
 * 闭锁：
 *      可以延迟线程的进度直到其到达终止状态
 *      闭锁可以用来确保某些活动直到其他活动都完成后再继续执行
 *
 *      CountDownLatch是一种灵活的闭锁实现，它可以使一个或多个线程等待一组事件发生
 *      FutureTask表示的计算是通过callable来实现的，相当于一种可生结果的runnable，并且可以处于三种状态：等待执行，正在运行，运行结束
 *          当进入完成状态后，它会永远停止在这个状态上
 */


/**
 * 2
 * 信号量：
 *      用来控制同时访问某个特定资源的操作数量，或者同时执行某个特定操作的数量
 */

/**
 * 3
 * 栅栏；
 *      栅栏类似于闭锁，它能阻塞一组线程直到某个事件发生。
 *      栅栏与闭锁的区别：
 *          所有线程都必须同时达到栅栏位置，才能继续执行。
 *          闭锁用于等待事件，为栅栏用于等待其他线程
 */
public class Test5 {
}