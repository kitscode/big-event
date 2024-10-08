package com.husky;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet() {
        //提供一个ThreadLocal对象
        ThreadLocal t1 = new ThreadLocal();

        //开启两个线程
        new Thread(() ->{
            t1.set("哈士奇");
            System.out.println(Thread.currentThread().getName() + ":" + t1.get());
            System.out.println(Thread.currentThread().getName() + ":" + t1.get());
            System.out.println(Thread.currentThread().getName() + ":" + t1.get());
        },"蓝色").start();

        new Thread(() -> {
            t1.set("中华田园犬");
            System.out.println(Thread.currentThread().getName() + ":" + t1.get());
            System.out.println(Thread.currentThread().getName() + ":" + t1.get());
            System.out.println(Thread.currentThread().getName() + ":" + t1.get());
        }, "黑色").start();

    }
}
