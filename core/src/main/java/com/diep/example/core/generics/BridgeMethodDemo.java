package com.diep.example.core.generics;

/**
 * Sometimes type erasure causes a situation that you may not have anticipated.
 * The following example shows how this can occur.
 * The example (described in Bridge Methods) shows how a compiler sometimes creates a synthetic method,
 * called a bridge method, as part of the type erasure process.
 */
public class BridgeMethodDemo {
    public static void main(String[] args) {
        BridgeMethodDemo demo = new BridgeMethodDemo();
        demo.showProblemWithErasure();
    }
    /**
     * This method does not have compile error but will have runtime error
     *
     * n.setData("Hello"); causes the method setData(Object) to be executed on the object of class MyNode.
     * (The MyNode class inherited setData(Object) from Node.)
     * bridge method tries to cast this String to Integer, which throws the exception
     */
    void showProblemWithErasure() {
        MyNode mn = new MyNode(5);
        Node n = mn;            // A raw type - compiler throws an unchecked warning
        n.setData("Hello");     // Causes a ClassCastException to be thrown.
        Integer x = mn.data;
    }

    // Type erasure replaces T with Object -> setData(Object data)
    class Node<T> {

        public T data;

        public Node(T data) { this.data = data; }

        public void setData(T data) {
            System.out.println("Node.setData");
            this.data = data;
        }
    }

    /**
     * After erasure, the method signatures of Node and MyNode do not match.
     * The Node method becomes setData(Object)
     * and the MyNode method becomes setData(Integer).
     * Therefore, the MyNode setData method does not override the Node setData method.
     *
     * To solve this problem and preserve the polymorphism of generic types after type erasure,
     * a Java compiler generates a bridge method to ensure that subtyping works as expected.
     * For the MyNode class, the compiler generates the following bridge method for setData:
     *
     *  public void setData(Object data) {
     *         setData((Integer) data);
     *  }
     */
    // Type erasure puts MyNode extends Node only
    class MyNode extends Node<Integer> {
        public MyNode(Integer data) { super(data); }

        public void setData(Integer data) {
            System.out.println("MyNode.setData");
            super.setData(data);
        }
    }
}
