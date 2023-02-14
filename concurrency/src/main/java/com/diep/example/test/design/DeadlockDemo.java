package com.diep.example.test.design;

/**
 * Deadlock happens when there is a dependency resource circle:
 * In this demo, the execution order is as follows:
 * 1. Thread A is holding lock on node 1
 * 2. Thread B is holding lock on node 2
 * 3. Thread A needs to lock on node 2
 * 4. Thread B needs to lock on node 1
 * Thread A cannot release lock on node 1 till it gains lock on node 2 and finishes method setParent()
 * Thread B cannot release lock on node 2 till it gains lock on node 1 and finishes method setChild()
 * None of the threads can move forwards and program hangs
 */
public class DeadlockDemo {
    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Thread t1 = new Thread(() -> one.setParent(two), "A");
        Thread t2 = new Thread(() -> two.setChild(one), "B");
        t1.start();
        t2.start();
    }

    static class Node {
        private final int id;
        private Node parent;
        private Node child;

        Node(int id) {
            this.id = id;
        }
        synchronized void setParent(Node parent) {
            System.out.println(Thread.currentThread().getName() + " locks on " + id);
            this.parent = parent;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + " going to lock on " + parent.id);
            parent.setChildOnly(this);
        }

        synchronized void setChild(Node child) {
            System.out.println(Thread.currentThread().getName() + " locks on " + id);
            this.child = child;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + " going to lock on " + child.id);
            child.setParentOnly(this);
        }

        synchronized void setParentOnly(Node parent) {
            this.parent = parent;
        }

        synchronized void setChildOnly(Node child) {
            this.child = child;
        }
    }
}
