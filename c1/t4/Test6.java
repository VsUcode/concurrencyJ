package com.vsu.c1.t4;

/** 方法
 * Created by vsu on 2018/01/28.
 */

/**
 * 1
 * getHoldCount() ：查询当前线程保持此锁定的个数，也就是调用lock方法的次数
 * getQueueLength()：返回正在等待获得此锁定的线程估计数
 * getWaitQueueLength()：返回等待与此锁定相关的给定条件condition的线程估计数
 */

/**
 * 2
 * hasQueuedThread()：查询指定线程是否正在等待获取此锁定
 * hasQueuedThreads()：查询是否有线程正在等待获取此锁定
 * hasWaiters()：查询是否有线程正在等待与此锁定有关的condition条件
 */

/**
 * 3
 * isFair()：判断是不是公平锁
 * isHeldByCurrentThread()：查询当前线程是否保持此锁定
 * isLocked()：查询此锁定是否由任意线程保持
 */

/**
 * 4
 * lockInterruptibly()：如果当前线程未被中断，则获取锁定。否则出现异常
 * tryLock()：仅在调用时锁定未被另一个线程保持的情况下，才获取该锁定
 * tryLock(long timeout, TimeUnit unit)：如果锁定在给定等待时间内没有被另外一个线程保持，且当前线程未被中断，则获取该锁定
 */


/**
 * 5
 * awaitUninterruptibly()：
 * awaitUntil()：
 */

/**
 * 6
 * 使用condition实现顺序执行
 */


public class Test6 {
}
