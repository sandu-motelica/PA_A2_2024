package org.example;

import java.lang.reflect.Parameter;

public class MockValue {
    public static Object generateMockValue(Class<?> parameterType) {
        if (parameterType == int.class) {
            return 0;
        } else if (parameterType == double.class) {
            return 0.0;
        } else if (parameterType == boolean.class) {
            return false;
        } else if (parameterType == char.class) {
            return '\u0000';
        } else if (parameterType == byte.class) {
            return (byte) 0;
        } else if (parameterType == short.class) {
            return (short) 0;
        } else if (parameterType == long.class) {
            return 0L;
        } else if (parameterType == float.class) {
            return 0.0f;
        } else if (parameterType == String.class) {
            return "string";
        }
        return null;
    }
    public static Object[] generateMockValues(Parameter[] parameters) {
        Object[] mockValues = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            mockValues[i] = generateMockValue(parameters[i].getType());
        }
        return mockValues;
    }
}
