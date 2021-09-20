package com.epam.jwd.concurrency;

public class MathSquare extends Thread {
    private final int value;

    private int result;

    public MathSquare(int value) {
        this.value = value;
        result = 0;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = value * value;
    }

    @Override
    public String toString() {
        return String.valueOf(result);
    }
}
