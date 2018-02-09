package com.vsu.c2.part1.t2;

/**
 * Created by vsu on 2018/02/04.
 */


/**
 * 线程封闭：
 *      仅在单线程内访问数据，就不需要同步。这种技术被称为 线程封闭
 *      在Swing中大量使用了线程封闭技术
 *      另一种常见应用就是JDBC
 */

/**
 * 1
 * Ad-hoc线程封闭：
 *      指；维护线程封闭性的职责完全由程序实现来承担
 *      当决定使用线程封闭技术时，通常是因为要将某个特定的子系统实现为一个单线程子系统
 *
 */


/**
 * 2
 * 栈封闭：
 *      栈封闭是线程封闭的一种特例，在栈封闭中，只能通过局部变量才能访问对象
 */

/**
 * 3
 * ThreadLocal类：
 *      这个类能使线程中的某个值与保存值的对象关联起来
 *      通常用于防止对可变的单shilling变量或全局变量进行共享
 *      当某个频繁执行的操作需要一个临时对象，例如一个缓冲区，而同时又希望避免在每次执行时都重新分配该临时对象
 */
public class Test3 {
}
