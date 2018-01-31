package com.vsu.c1.t3;

/**
 * Created by vsu on 2018/01/25.
 */


/**
 * 1
 * 方法wait锁释放与notify锁不释放
 * 当方法wait被执行后，锁被自动释放，但执行完notify方法，锁却不自动释放
 * 必须执行完notify方法所在的同步synchronized代码块后才释放锁
 */


/**
 * 2
 * 当interrupt方法遇到了wait方法
 * 当线程呈wait状态时，调用线程对象的interrupt方法会出现InterruptedException异常
 */


/**
 * 3
 * wait(long)带一个参数时，在这个时间内没被唤醒将在超过这个时间自动唤醒
 */

/**
 * 4
 * 在使用wait/notify模式时，wait等待的条件发生了变化，也容易造成程序逻辑的混乱
 */
public class Test2 {

}
