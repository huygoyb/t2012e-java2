package com.t2012e.javathread;

public class DemoThread {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        MyThread2 coreThread1 = new MyThread2();
        coreThread1.setType(1);
        MyThread2 coreThread2 = new MyThread2();
        coreThread2.setType(2);

        coreThread1.setMyThread2(coreThread2);
        coreThread2.setMyThread2(coreThread1);

        Thread t1 = new Thread(coreThread1);
        t1.setName("Quý");

        Thread t2 = new Thread(coreThread2);
        t2.setName("Nguyên");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " mls");
        System.out.println("Okie, done!");
    }
}
