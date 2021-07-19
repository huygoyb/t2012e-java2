package com.t2012e.javathread;

// C1 extend Thread
public class MyThread extends Thread {

    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            count++;
            System.out.printf("%s count %d\n", this.getName(), count);
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
