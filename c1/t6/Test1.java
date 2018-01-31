package com.vsu.c1.t6;

/** 单例模式与多线程[单例模式看设计模式]
 * Created by vsu on 2018/01/30.
 */


import java.io.Serializable;

/**
 * 5
 * 序列化与反序列化的单例模式的实现
 */
class MyObject implements Serializable{
    private static final long serialVersionUID = 888L;

    private static class MyObjectHandler{
        private static final MyObject myObject = new MyObject();
    }

    private MyObject(){}

    public static MyObject getInstance(){
        return MyObjectHandler.myObject;
    }

    protected Object readResolve(){
        System.out.println("调用了 readResolve方法");
        return MyObjectHandler.myObject;
    }
}



public class Test1 {
}
