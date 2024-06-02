package org.example;

public class SampleClass {

    @Test
    public static void testMethod1() {
        System.out.println("testMethod1 executed.");
    }

    @Test
    public static void testMethod2() {
        System.out.println("testMethod2 executed.");
    }

    public static void regularMethod() {
        System.out.println("regularMethod executed.");
    }

    @Test
    public void nonStaticTestMethod() {
        System.out.println("nonStaticTestMethod executed.");
    }
}

