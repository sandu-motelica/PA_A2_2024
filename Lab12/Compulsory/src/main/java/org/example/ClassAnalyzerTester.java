package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassAnalyzerTester {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ClassAnalyzerTester <fully-qualified-class-name>");
            return;
        }

        String className = args[0];

        try {
            // Load the class
            Class<?> clazz = Class.forName(className);

            System.out.println("Class: " + clazz.getName());

            // Extract and display methods information
            Method[] methods = clazz.getDeclaredMethods();
            System.out.println("Methods:");
            for (Method method : methods) {
                System.out.println("  " + method.toString());

                // Check if method is annotated with @Test
                if (method.isAnnotationPresent(Test.class)) {
                    // Ensure the method is static and takes no arguments
                    if (Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0) {
                        System.out.println("    Invoking @Test method: " + method.getName());
                        // Invoke the method
                        method.invoke(null);
                    } else {
                        System.out.println("    @Test method not static or takes arguments, skipping: " + method.getName());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred while analyzing or invoking methods.");
            e.printStackTrace();
        }
    }
}
