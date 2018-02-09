package com.vsu.c2.part1.t4;

/** 阻塞方法和中断方法：
 *
 * Created by vsu on 2018/02/09.
 */


/**
 * 当在代码中调用了一个将抛出InterruptedException异常的方法时，你自己的方法也就变成了一个阻塞的方法，并且必须
 *      要处理对中断的响应：
 *          1 传递InterruptedException。避开免这个异常通常是最明智的策略--只需把InterruptedException传递给方法的调用者
 *              传递InterruptedException的方法包括：不捕获，或者捕获再次抛出
 *          2 恢复中断。
 */
public class Test4 {
}
