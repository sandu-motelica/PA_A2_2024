package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class ClassAnalyzerTester {
    private static int totalTests = 0;
    private static int successfulTests = 0;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -cp . org.example.ClassAnalyzerTester <path-to-class-or-folder-or-jar>");
            return;
        }

        File input = new File(args[0]);
        if (input.exists()) {
            if (input.isFile()) {
                if (args[0].endsWith(".class")) {
                    processClassFile(input, input.getParentFile().getPath());
                } else if (args[0].endsWith(".jar")) {
                    processJarFile(input);
                }
            } else if (input.isDirectory()) {
                processDirectory(input, input.getPath());
            }
        } else {
            System.out.println("The specified path does not exist: " + args[0]);
        }

        printStatistics();
    }

    private static void processClassFile(File classFile, String rootPath) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(rootPath).toURI().toURL()});
            String className = getClassNameFromFile(classFile, rootPath);
            System.out.println("Class name: " + className); // Debugging output
            Class<?> clazz = Class.forName(className, true, classLoader);
            analyzeClass(clazz);
        } catch (Exception e) {
            System.out.println("Error processing class file: " + classFile.getPath());
            e.printStackTrace();
        }
    }

    private static void processDirectory(File directory, String rootPath) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file, rootPath);
                } else if (file.getName().endsWith(".class")) {
                    processClassFile(file, rootPath);
                }
            }
        }
    }

    private static void processJarFile(File jarFile) {
        try (JarFile jar = new JarFile(jarFile)) {
            URL[] urls = {new URL("jar:file:" + jarFile.getPath() + "!/")};
            URLClassLoader classLoader = URLClassLoader.newInstance(urls);
            jar.stream().forEach(entry -> {
                if (entry.getName().endsWith(".class")) {
                    try {
                        String className = entry.getName().replace('/', '.').replace(".class", "");
                        System.out.println("Class name: " + className); // Debugging output
                        Class<?> clazz = Class.forName(className, true, classLoader);
                        analyzeClass(clazz);
                    } catch (ClassNotFoundException e) {
                        System.out.println("Class not found in jar: " + entry.getName());
                    } catch (Exception e) {
                        System.out.println("Error processing class in jar: " + entry.getName());
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("Error processing jar file: " + jarFile.getPath());
            e.printStackTrace();
        }
    }

    private static String getClassNameFromFile(File classFile, String rootPath) {
        String path = classFile.getPath();
        String className = path.substring(rootPath.length() + 1, path.length() - 6).replace(File.separator, ".");
        return className;
    }

    private static void analyzeClass(Class<?> clazz) {
        if (!Modifier.isPublic(clazz.getModifiers())) {
            return; // Skip non-public classes
        }

        System.out.println("Class: " + clazz.getName());

        Method[] methods = clazz.getDeclaredMethods();
        List<Method> testMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }

        for (Method method : testMethods) {
            invokeTestMethod(clazz, method);
        }
    }

    private static void invokeTestMethod(Class<?> clazz, Method method) {
        try {
            Object instance = null;
            if (!Modifier.isStatic(method.getModifiers())) {
                instance = clazz.getConstructor().newInstance();
            }

            Parameter[] parameters = method.getParameters();
            Object[] mockValues = MockValue.generateMockValues(parameters);

            System.out.println("Invoking @Test method: " + method.getName() + " from class: " + clazz.getName());
            System.out.print("Parameters: ");
            for (int i = 0; i < parameters.length; i++) {
                System.out.print(parameters[i].getType().getSimpleName() + " = " + mockValues[i]);
                if (i < parameters.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();

            method.setAccessible(true);
            method.invoke(instance, mockValues);

            System.out.println("Test passed: " + method.getName());
            successfulTests++;
        } catch (Exception e) {
            System.out.println("Test failed: " + method.getName());
            e.printStackTrace();
        } finally {
            totalTests++;
        }
    }

    private static void printStatistics() {
        System.out.println("Total tests: " + totalTests);
        System.out.println("Successful tests: " + successfulTests);
        System.out.println("Failed tests: " + (totalTests - successfulTests));
    }
}
