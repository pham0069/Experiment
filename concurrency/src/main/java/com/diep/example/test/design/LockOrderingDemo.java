package com.diep.example.test.design;

/**
 * https://www.schoolofhaskell.com/school/advanced-haskell/beautiful-concurrency/2-a-simple-example-bank-accounts
 *http://tutorials.jenkov.com/java-concurrency/deadlock-prevention.html
 *
 * Given a singleton account manager that could be accessed by multiple threads, what could be good implementation ?
 *
 * In this demo, we have different implementations of AccountManager:
 * 1. UnscalableAccountManager: implement synchronized transfer() method.
 * This is thread-safe but unscalable since only one thread can transfer at a time.
 * It is observed that when a transfer is being done on two accounts, it is totally safe to do a transfer on other two
 * accounts at the same time.
 *
 * 2. InconsistentAccountManager: from above observation, we can think of locking on sender and receiver account object,
 * instead of locking on account manager object.
 * The implementation is equivalent to put synchronized on withdraw() and deposit() methods of Account class.
 * This is more scalable, but leaves some intermediate state in which money has left sender account, but not arrived to
 * receiver account, which is after sender.withdraw() and before receiver.deposit().
 * This period could be long if receiver account is blocked somewhere else for long time.
 * During this period, the money is absent from both accounts, which is unacceptable for finance program
 *
 * 3. DeadlockAccountManager: obtain two locks in nested way, before actually transferring the money
 * This is prone to deadlock if two transfers from A to B, and from B to A, are executed at the same time
 *
 * 4. SafeAccountManager: resolve deadlock by locking the account with smaller id first.
 * Even if two transfers from A to B, and from B to A, the locks are obtained in same order, preventing deadlock from
 * happening
 *
 * ---------------------------------------------------------------------------------------------------------------------
 * Other deadlock prevention mechanisms:
 *
 * 1. Lock timeout: if a thread cannot get all locks necessary within a given timeout, it will backup, free all locks
 * take, wait for random amount of time and retry
 * 2. Deadlock detection: used when lock ordering isnt possible, lock timeout isnt feasible
 * Use a DS to record the locks that a thread takes or requests and to check if deadlock might happen
 *
 * After deadlock detection, one possible action is release all locks, backup, wait random, retry. Howver, if a lot of
 * threads are competing for same locks, they may repeatedly end up in a deadlock again after backup and wait
 *
 * Another option is assign a priority of the threads so that only one (or few) thread backs up while the rest continues
 */
public class LockOrderingDemo {
    static class UnscalableAccountManager implements AccountManager {
        @Override
        public synchronized void transfer(Account sender, Account receiver, double amount) {
            if (sender.withdraw(amount))
                receiver.deposit(amount);
        }
    }

    static class InconsistentAccountManager implements AccountManager {
        @Override
        public void transfer(Account sender, Account receiver, double amount) {
            boolean withdrawn;
            synchronized (sender) {
                withdrawn = sender.withdraw(amount);
            }

            synchronized (receiver) {
                if (withdrawn)
                    receiver.deposit(amount);
            }
        }
    }

    static class DeadlockAccountManager implements AccountManager {
        @Override
        public void transfer(Account sender, Account receiver, double amount) {
            synchronized (sender) {
                synchronized (receiver) {
                    if (sender.withdraw(amount))
                        receiver.deposit(amount);
                }
            }
        }
    }

    static class SafeAccountManager implements AccountManager {
        @Override
        public void transfer(Account sender, Account receiver, double amount) {
            Account first, second;
            if (sender.id < receiver.id) {
                first = sender;
                second = receiver;
            } else {
                first = receiver;
                second = sender;
            }
            synchronized (first) {
                synchronized (second) {
                    if (sender.withdraw(amount))
                        receiver.deposit(amount);
                }
            }
        }
    }



    interface AccountManager {
        void transfer(Account sender, Account receiver, double amount);
    }

    static class Account {
        int id;
        double balance;
        public void deposit(double amount) {
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (balance < amount) {
                return false;
            }
            balance -= amount;
            return true;
        }
    }
}
