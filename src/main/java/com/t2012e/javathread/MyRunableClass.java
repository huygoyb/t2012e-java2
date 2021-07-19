package com.t2012e.javathread;

public class MyRunableClass implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.printf("Hi, i am thread %s\n", Thread.currentThread().getName());
                Thread.sleep(1* 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
