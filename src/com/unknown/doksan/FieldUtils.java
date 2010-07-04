package com.unknown.doksan;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;

public class FieldUtils {
    private static final String GETTER_METHOD_PREFIX = "get";

    public static List<Assertion> buildAssertionObjects2(Object object, List<Class> classes) throws Exception {
        List<String> getterMethodNames = ReflectionUtils.findMethodNamesByPartialName(object, GETTER_METHOD_PREFIX);
        return buildAssertionsFromGetterMethodNames(object, getterMethodNames, null, classes);
    }

    private static List<Assertion> buildAssertionsFromGetterMethodNames(Object object, List<String> getterMethodNames, String parentClassName, List<Class> classes) throws Exception {
        ArrayList<Assertion> assertions = new ArrayList<Assertion>();
        for (String getterMethodName : getterMethodNames)
            buildAssertion(object, parentClassName, assertions, getterMethodName, classes);
        return assertions;
    }

    private static void buildAssertion(Object object, String parentClassName, ArrayList<Assertion> assertions, String getterMethodName, List<Class> classes) throws Exception {
        Method method = ReflectionUtils.findMethodByName(object, getterMethodName);
        Object fieldValue = ReflectionUtils.invokeMethod(object, method);
        String fieldName = ReflectionUtils.getFieldNameFromMethodName(getterMethodName);
        handleAssertionTypes(assertions, method.getReturnType(), getClassName(parentClassName, ReflectionUtils.getClassName(object)), fieldValue, fieldName, classes);
    }

    private static void handleAssertionTypes(ArrayList<Assertion> assertions, Class<?> returnType, String className, Object fieldValue, String fieldName, List<Class> classes) throws Exception {
        if (fieldValue != null && classes != null && classes.contains(fieldValue.getClass())) {
            List<String> getterMethodNames = ReflectionUtils.findMethodNamesByPartialName(fieldValue, GETTER_METHOD_PREFIX);
            assertions.addAll(buildAssertionsFromGetterMethodNames(fieldValue, getterMethodNames, className, classes));
        }
        else if (fieldValue instanceof Collection)
            buildAssertionsFromCollection(assertions, className, fieldValue, fieldName);
        else
            buildAssertionFromStandardType(assertions, returnType, className, fieldValue, fieldName);
    }

    private static void buildAssertionsFromCollection(ArrayList<Assertion> assertions, String className, Object fieldValue, String fieldName) {
        Iterator iterator = ((Collection) fieldValue).iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Object itemFieldValue = iterator.next();
            assertions.add(new Assertion(className, buildArrayIndexString(fieldName, index++), itemFieldValue, itemFieldValue.getClass()));
        }
    }

    private static String buildArrayIndexString(String fieldName, int index) {
        return MessageFormat.format("{0}[{1}]", fieldName, index);
    }

    private static void buildAssertionFromStandardType(ArrayList<Assertion> assertions, Class<?> returnType, String className, Object fieldValue, String fieldName) {
        assertions.add(new Assertion(className, fieldName, fieldValue, returnType));
    }

    private static String getClassName(String parentClassName, String className) {
        return parentClassName == null ? className : MessageFormat.format("{0}.{1}", parentClassName, className);
    }
}