package com.unknown.doksan;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {
    private static final String GETTER_METHOD_PREFIX = "get";

    public static String lowerCaseFirstChar(String string) {
        return (string == null || string.isEmpty()) ? string : new StringBuilder().append(string.substring(0, 1).toLowerCase()).append(string.substring(1)).toString();
    }

    public static String getClassName(Object object) {
        return lowerCaseFirstChar(object.getClass().getSimpleName());
    }

    public static String getFieldNameFromMethodName(String methodName) {
        return lowerCaseFirstChar(methodName.replace(GETTER_METHOD_PREFIX, ""));
    }

    public static List<Method> getMethodsAndSuperMethods(Object object) {
        ArrayList<Method> methodsList = new ArrayList<Method>();
        methodsList.addAll(Arrays.asList(object.getClass().getDeclaredMethods()));
        methodsList.addAll(getSuperClassMethods(object));
        return methodsList;
    }

    private static List<Method> getSuperClassMethods(Object object) {
        // TODO: will only get methods from one superclass up, extend to recurse up through all parents until Object if required
        // TODO: some classes may have superclasses that we don't wish to consider
        ArrayList<Method> methods = new ArrayList<Method>();
        Class superClass = object.getClass().getSuperclass();
        if (superClass != Object.class)
            return Arrays.asList(superClass.getDeclaredMethods());
        return methods;
    }

    public static Object invokeMethod(Object object, Method method) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(object);
    }

    public static Method findMethodByName(Object object, String methodName) throws NoSuchMethodException {
        return object.getClass().getMethod(methodName);
    }

    public static List<String> findMethodNamesByPartialName(Object object, String partialName) {
        ArrayList<String> methodNames = new ArrayList<String>();
        for (Method method : getMethodsAndSuperMethods(object))
            if (method.getName().contains(partialName))
                methodNames.add(method.getName());
        return methodNames;
    }
}