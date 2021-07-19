package com.t2012e.deadlock;

public class DemoDeadLock {
    public static void main(String[] args) {
        DeadlockDemo dead1 = new DeadlockDemo();
        DeadlockDemo dead2 = new DeadlockDemo();
        dead1.grabIt = dead2;
        dead2.grabIt = dead1;

        Thread t1 = new Thread(dead1);
        Thread t2 = new Thread(dead2);
        t1.start();
        t2.start();
    }
}
