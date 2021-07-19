package com.t2012e.javathread;

public class MyThread2 implements Runnable {

    private int countA = 0;
    private int countB = 0;
    private int type;
    private MyThread2 myThread2;

    public MyThread2 getMyThread2() {
        return myThread2;
    }

    public void setMyThread2(MyThread2 myThread2) {
        this.myThread2 = myThread2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void run() {
        System.out.printf("Thread %s started.\n", Thread.currentThread().getName());
        if (type == 1) {
            increaseA();
        } else {
            increaseB();
        }
    }

    public synchronized void increaseA() {
        System.out.printf("Thread %s have lock.\n", Thread.currentThread().getName());
        for (int i = 0; i < 5; i++) {
            countA++;
            System.out.printf("%s countA %d\n", Thread.currentThread().getName(), countA);
            try {
                Thread.sleep(5 * 1000);
                myThread2.increaseB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Thread %s release lock.\n", Thread.currentThread().getName());
    }

    public synchronized void increaseB() {
        System.out.printf("Thread %s have lock.\n", Thread.currentThread().getName());
        for (int i = 0; i < 5; i++) {
            countB++;
            System.out.printf("%s countB %d\n", Thread.currentThread().getName(), countB);
            try {
                Thread.sleep(5 * 1000);
                myThread2.increaseA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Thread %s release lock.\n", Thread.currentThread().getName());
    }
}
