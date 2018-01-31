package com.vsu.c1.t5;

/**
 * Created by vsu on 2018/01/29.
 */

/**
 * 方法scheduleAtFixedRate(TimerTask task,Date time, long delay)的测试：
 *
 * scheduleAtFixedRate 和 schedule ：
 *      1 都会按顺序执行，不需考虑非线程安全的情况
 *   区别只在于不延迟：
 *      2 schedule：如果没有被延迟，那么下一次任务的执行时间参考的 上一次任务的开始时间
 *      3 scheduleAtFixedRate：如果没有被延迟，那么下一次任务的执行时间参考的 上一次任务的结束时间
 *      4 schedule不具有追赶执行性：当前时间之前的任务不执行
 *      5 scheduleAtFixedRate：具有追赶执行性：补充性执行
 *
 */
public class Test2 {
}
