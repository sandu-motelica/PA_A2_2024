package org.example;

public class SampleClass {

    @Test
    public static void testMethod1() {
        System.out.println("testMethod1 executed.");
    }

    @Test
    public void testMethod2() {
        System.out.println("testMethod2 executed.");
    }

    @Test
    public void testMethodWithParams(int a, String b) {
        System.out.println("testMethodWithParams executed with values: " + a + ", " + b);
    }

    public void method() {
        System.out.println("method executed.");
    }

    @Test
    public static void staticTestMethodWithParams(int a, double b) {
        System.out.println("staticTestMethodWithParams executed with values: " + a + ", " + b);
    }
}

