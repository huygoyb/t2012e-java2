package com.t2012e.deadlock;

public class DeadlockDemo implements Runnable{

    DeadlockDemo grabIt;

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        grabIt.syncIt();
        System.out.println("Finish it");
    }

    public synchronized void syncIt(){
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("In sync method.");
    }
}
