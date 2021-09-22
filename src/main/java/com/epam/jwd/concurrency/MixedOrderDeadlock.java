package com.epam.jwd.concurrency;

public class MixedOrderDeadlock {

    private final Object first = new Object();
    private final Object second = new Object();

    public void firstSecond() {
        synchronized (first) {
            synchronized (second) {
                doSomething();
            }
        }
    }

    public void secondFirst() {
        synchronized (second) {
            synchronized (first) {
                doSomething();
            }
        }
    }

//    public void secondFirst() { //todo: correct order!!!
//        synchronized (first) {
//            synchronized (second) {
//                doSomething();
//            }
//        }
//    }

    private void doSomething() {
        //some code;
    }

}
