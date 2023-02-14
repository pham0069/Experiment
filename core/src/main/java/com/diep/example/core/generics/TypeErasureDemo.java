package com.diep.example.core.generics;

/**
 * Generics were introduced to the Java language to:
 * 1. provide tighter type checks at compile time
 * 2. and support generic programming.
 *
 * To implement generics, the Java compiler applies type erasure to:
 * 1. Replace all type parameters in generic types with their bounds or Object,
 * if the type parameters are unbounded.
 * The produced bytecode, therefore, contains only ordinary classes, interfaces, and methods.
 * 2. Insert type casts if necessary to preserve type safety.
 * 3. Generate bridge methods to preserve polymorphism in extended generic types.
 *
 * Type erasure ensures that no new classes are created for parameterized types;
 * consequently, generics incur no runtime overhead.
 * =====================================================================================================================
 * A reifiable type is a type whose type information is fully available at runtime.
 * This includes primitives, non-generic types, raw types, and invocations of unbound wildcards.
 *
 * Non-reifiable types are types where information has been removed at compile-time by type erasure
 * — invocations of generic types that are not defined as unbounded wildcards.
 * A non-reifiable type does not have all of its information available at runtime.
 */
public class TypeErasureDemo {
    /**
     * Since T is unbounded, Java replaces T with Object during type erasure
     * public class Node {
     *
     *     private Object data;
     *     private Node next;
     *
     *     public Node(Object data, Node next) {
     *         this.data = data;
     *         this.next = next;
     *     }
     *
     *     public Object getData() { return data; }
     *     // ...
     * }
     * @param <T>
     */
    public class Node<T> {

        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() { return data; }
        // ...
    }

    /**
     * Since T is bounded to Comparable, it is replaced with Comparable
     * public class Node2 {
     *
     *     private Comparable data;
     *     private Node2 next;
     *
     *     public Node2(Comparable data, Node2 next) {
     *         this.data = data;
     *         this.next = next;
     *     }
     *
     *     public Comparable getData() { return data; }
     *     // ...
     * }
     * @param <T>
     */
    public class Node2<T extends Comparable<T>> {

        private T data;
        private Node2<T> next;

        public Node2(T data, Node2<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() { return data; }
        // ...
    }

}

