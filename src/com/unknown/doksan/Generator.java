package com.unknown.doksan;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;

public class Generator {
    private static final String GETTER_METHOD_PREFIX = "get";
    // todo: rename
    private Set<Object> _classes;

    public Generator() {
        _classes = new HashSet<Object>();
    }

    public void addClass(Class clazz) {
        _classes.add(clazz);
    }

    public List<String> buildAssertions(Object object) throws Exception {
        return buildAssertionStrings(buildAssertionObjects(object));
    }

    private List<Assertion> buildAssertionObjects(Object fieldValue, String parentClassName) throws Exception {
        List<String> getterMethodNames = ReflectionUtils.findMethodNamesByPartialName(fieldValue, GETTER_METHOD_PREFIX);
        return buildAssertionsFromGetterMethodNames(fieldValue, getterMethodNames, parentClassName);
    }

    private List<Assertion> buildAssertionsFromGetterMethodNames(Object object, List<String> getterMethodNames, String parentClassName) throws Exception {
        ArrayList<Assertion> assertions = new ArrayList<Assertion>();
        for (String getterMethodName : getterMethodNames)
            buildAssertion(object, parentClassName, assertions, getterMethodName);
        return assertions;
    }

    private void buildAssertion(Object object, String parentClassName, ArrayList<Assertion> assertions, String getterMethodName) throws Exception {
        Method method = ReflectionUtils.findMethodByName(object, getterMethodName);
        Object fieldValue = ReflectionUtils.invokeMethod(object, method);
        String fieldName = ReflectionUtils.getFieldNameFromMethodName(getterMethodName);
        handleAssertionTypes(assertions, method.getReturnType(), getClassName(parentClassName, ReflectionUtils.getClassName(object)), fieldValue, fieldName);
    }

    private void handleAssertionTypes(ArrayList<Assertion> assertions, Class<?> returnType, String className, Object fieldValue, String fieldName) throws Exception {
        if (fieldValue != null && _classes != null && _classes.contains(fieldValue.getClass()))
            assertions.addAll(buildAssertionObjects(fieldValue, className));
        else if (fieldValue instanceof Collection)
            buildAssertionsFromCollection(assertions, className, fieldValue, fieldName);
        else
            buildAssertionFromStandardType(assertions, returnType, className, fieldValue, fieldName);
    }

    private void buildAssertionsFromCollection(ArrayList<Assertion> assertions, String className, Object fieldValue, String fieldName) {
        Iterator iterator = ((Collection) fieldValue).iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Object itemFieldValue = iterator.next();
            assertions.add(new Assertion(className, buildArrayIndexString(fieldName, index++), itemFieldValue, itemFieldValue.getClass()));
        }
    }

    private String buildArrayIndexString(String fieldName, int index) {
        return MessageFormat.format("{0}[{1}]", fieldName, index);
    }

    private List<String> buildAssertionStrings(List<Assertion> assertions) {
        ArrayList<String> assertionStrings = new ArrayList<String>();
        for (Assertion assertion : assertions)
            assertionStrings.add(assertion.buildAssertionString());
        return assertionStrings;
    }

    private void buildAssertionFromStandardType(ArrayList<Assertion> assertions, Class<?> returnType, String className, Object fieldValue, String fieldName) {
        assertions.add(new Assertion(className, fieldName, fieldValue, returnType));
    }

    private List<Assertion> buildAssertionObjects(Object fieldValue) throws Exception {
        return buildAssertionObjects(fieldValue, null);
    }

    private String getClassName(String parentClassName, String className) {
        return parentClassName == null ? className : MessageFormat.format("{0}.{1}", parentClassName, className);
    }
}