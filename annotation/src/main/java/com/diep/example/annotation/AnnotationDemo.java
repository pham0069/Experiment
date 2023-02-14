package com.diep.example.annotation;

import com.sun.istack.internal.Interned;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation is a form of metadata
 * It does not have direct effect on the operation of the code they annotate
 * Used by annotation processor to generate files or provide informational messages:
 * 1. Information for compiler
 * detect errors or suppress warnings
 * 2. Compile-time and deployment-time processing
 * sw tools process annotation to generate code, XML files...
 * 3. Runtime processing
 * examined at runtime
 * ---------------------------------------------------------------------------------------------------------------------
 * Embedded Java annotations
 * 1. @Deprecated
 * indicate that marked element should no longer be used
 * cause compiler to generate warning if marked element is found in source code
 * 2. @Override
 * indicate that marked element overrides another method defined in its super class
 * compiler will generate warning if marked method is not a method overridden superclass
 * 3. @SuppressWarning
 * if marked element generate warnings, compiler should suppress them
 * 4. @SafeVarargs
 * indicate that marked element (method/constructor) does not perform potentially unsafe operations via varargs parameters
 * cause compiler to suppress unchecked warnings related to varargs
 * 5. @FunctionalInterface:
 * indicate that the type declaration is intended to be a functional interface
 * ---------------------------------------------------------------------------------------------------------------------
 * Annotation can be used for
 * 1. Method
 * 2. Type
 *
 * Java 8 does not include any annotations specific to types
 * libraries such as Checker Framework contain annotations that can be applied o types for verifying certain criteria
 * Define a type-use annotation @Test to demonstrate the syntax of type use
 */
public class AnnotationDemo {
    public static void main(String[] args) {
        suppressWarningDemo("100");
        typeAnnotationDemo();
    }

    @SuppressWarnings(value="unchecked")
    static int suppressWarningDemo(Object object) {
        return Integer.parseInt((String) object);
    }

    static void typeAnnotationDemo() {
        // class instance creation, including array, generic
        @Test Object string = new @Test String();
        int @Test [] array = new int @Test[5];

        // type cast
        String copy = (@Test String) string;

        // catch exception
        try {
        } catch (@Test Exception e) {}
    }

    /**
     * Define a type use annotation
     */
    @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
    public @interface Test {}
}
