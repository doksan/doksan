package com.unknown.doksan;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;

public class FieldUtils {
    private static final String GETTER_METHOD_PREFIX = "get";

    public static List<Field> getFields(Object object, List<Class> classesToScan) throws Exception {
        List<String> getterMethodNames = ReflectionUtils.findMethodNamesByPartialName(object, GETTER_METHOD_PREFIX);
        return buildAssertionsFromGetterMethodNames(object, getterMethodNames, null, classesToScan);
    }

    private static List<Field> buildAssertionsFromGetterMethodNames(Object object, List<String> getterMethodNames, String parentClassName, List<Class> classesToScan) throws Exception {
        ArrayList<Field> fields = new ArrayList<Field>();
        for (String getterMethodName : getterMethodNames)
            buildAssertion(object, parentClassName, fields, getterMethodName, classesToScan);
        return fields;
    }

    private static void buildAssertion(Object object, String parentClassName, ArrayList<Field> fields, String getterMethodName, List<Class> classesToScan) throws Exception {
        Method method = ReflectionUtils.findMethodByName(object, getterMethodName);
        Object fieldValue = ReflectionUtils.invokeMethod(object, method);
        String fieldName = ReflectionUtils.getFieldNameFromMethodName(getterMethodName);
        handleAssertionTypes(fields, method.getReturnType(), getClassName(parentClassName, ReflectionUtils.getClassName(object)), fieldValue, fieldName, classesToScan);
    }

    private static void handleAssertionTypes(ArrayList<Field> fields, Class<?> returnType, String className, Object fieldValue, String fieldName, List<Class> classesToScan) throws Exception {
        if (fieldValue != null && classesToScan != null && classesToScan.contains(fieldValue.getClass())) {
            List<String> getterMethodNames = ReflectionUtils.findMethodNamesByPartialName(fieldValue, GETTER_METHOD_PREFIX);
            fields.addAll(buildAssertionsFromGetterMethodNames(fieldValue, getterMethodNames, className, classesToScan));
        }
        else if (fieldValue instanceof Collection)
            buildAssertionsFromCollection(fields, className, fieldValue, fieldName);
        else
            buildAssertionFromStandardType(fields, returnType, className, fieldValue, fieldName);
    }

    private static void buildAssertionsFromCollection(ArrayList<Field> fields, String className, Object fieldValue, String fieldName) {
        Iterator iterator = ((Collection) fieldValue).iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Object itemFieldValue = iterator.next();
            fields.add(new Field(className, buildArrayIndexString(fieldName, index++), itemFieldValue, itemFieldValue.getClass()));
        }
    }

    private static String buildArrayIndexString(String fieldName, int index) {
        return MessageFormat.format("{0}[{1}]", fieldName, index);
    }

    private static void buildAssertionFromStandardType(ArrayList<Field> fields, Class<?> returnType, String className, Object fieldValue, String fieldName) {
        fields.add(new Field(className, fieldName, fieldValue, returnType));
    }

    private static String getClassName(String parentClassName, String className) {
        return parentClassName == null ? className : MessageFormat.format("{0}.{1}", parentClassName, className);
    }
}