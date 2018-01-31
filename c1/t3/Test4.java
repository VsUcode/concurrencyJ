package com.vsu.c1.t3;

/**
 * Created by vsu on 2018/01/26.
 */


/**
 * 1
 * 方法join的使用：作用是等待线程对象销毁
 * 在很多情况下，主线程创建并启动子线程，如果子线程要进行大量的耗时运算，主线程往往将早于子线程结束之前结束。
 */


/**
 * 2
 * 方法join具有使线程排队运行的作用，有些类似同步的运行效果。
 * join与synchronized的区别：
 *      join在内部使用wait方法进行等待，而synchronized关键字使用的是“对象监视器”原理作为同步
 *      join方法具有释放锁的特点
 */


/**
 * 3
 * join与异常
 * 在join过程中，如果当前线程对象被中断，则当前线程出现异常
 * join方法与interrupt方法如果彼此遇到，则会出现异常
 */

/**
 * 4
 * join(long)与sleep(long)的区别
 * 前者释放锁，后者不释放
 */


/**
 * 方法join后面的代码提前运行：出现意外
 */


public class Test4 {

}
