package com.unknown.doksan;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FieldAssertionGenerator {
    private static final String COMMAND_NAME = "AssertExpression";

    public static List<String> buildAssertions(Object object, List<Class> classes) throws Exception {
        return buildAssertionStrings(FieldUtils.getFields(object, classes));
    }

    private static List<String> buildAssertionStrings(List<Field> fields) {
        ArrayList<String> assertionStrings = new ArrayList<String>();
        for (Field field : fields)
            assertionStrings.add(buildAssertionString(field.getValue(), field.getType(), field.getClassName(), field.getName()));
        return assertionStrings;
    }

    public static String buildAssertionString(Object value, Type type, String className, String name) {
        if (value != null && (type == String.class || type == Date.class))
            return formatAssertionString("'", "'", className, name, value);
        else
            return formatAssertionString("", "", className, name, value);

    }

    private static String formatAssertionString(String valuePrefix, String valueSuffix, String className, String name, Object value) {
        return COMMAND_NAME + "   \"" +
                className + "." + name +
                " == " +
                valuePrefix + value + valueSuffix + "\"";
    }
}