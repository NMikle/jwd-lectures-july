package com.epam.jwd.concurrency;

import com.epam.jwd.model.Account;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {

    private final int[] accounts;

    private final ReentrantLock lock;
    private final Condition sufficientFunds;

    public ConcurrentBank(int[] accounts) {
        this.accounts = accounts;
        this.lock = new ReentrantLock();
        this.sufficientFunds = lock.newCondition();
    }

//    public void transferMoney(int from, int to, int amount) throws InterruptedException {
//        lock.lock();
//        try {
//            while (accounts[from] < amount) {
//                sufficientFunds.await();
//            }
//            accounts[from] -= amount;
//            accounts[to] += amount;
//            sufficientFunds.signalAll();
//        } finally {
//            lock.unlock();
//        }
//    }

//    public void transferMoney(int from, int to, int amount) throws InterruptedException {
//        synchronized (this) {
//            while (accounts[from] < amount) {
//                this.wait();
//            }
//            accounts[from] -= amount;
//            accounts[to] += amount;
//            this.notifyAll();
//        }
//    }

//    private final Object o = new Object();
//    public void transferMoney(int from, int to, int amount) throws InterruptedException {
//        synchronized (o) {
//            while (accounts[from] < amount) {
//                o.wait();
//            }
//            accounts[from] -= amount;
//            accounts[to] += amount;
//            o.notifyAll();
//        }
//    }

    public synchronized void transferMoney(int from, int to, int amount) throws InterruptedException {
        while (accounts[from] < amount) {
            this.wait();
        }
        accounts[from] -= amount;
        accounts[to] += amount;
        this.notifyAll();
    }

    public synchronized static void hello() {
        synchronized (ConcurrentBank.class) {
            System.out.println("Hello");
        }
    }

    public void transferMoney(Account from, Account to, int amount) { //deadlock because parameters could be mixed up
        synchronized (from) {
            synchronized (to) {
                if (from.getBalance() < amount) {
                    throw new IllegalStateException("not enough money");
                }
                from.debit(amount);
                to.credit(amount);
            }
        }
    }

//    public synchronized static void hello() {
//        System.out.println("Hello");
//    }

    public int getAmount(int person) {
        return accounts[person];
    }

}
