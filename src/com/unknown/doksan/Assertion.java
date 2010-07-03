package com.unknown.doksan;

import java.lang.reflect.Type;
import java.util.Date;

public class Assertion {
        private String _fieldName;
        private Object _fieldValue;
        private Type _fieldType;
        private String _className;
        private static final String COMMAND_NAME = "AssertExpression";

        Assertion(String className, String fieldName, Object fieldValue, Type fieldType) {
            _className = className;
            _fieldName = fieldName;
            _fieldValue = fieldValue;
            _fieldType = fieldType;
        }

        public String getFieldName() {
            return _fieldName;
        }

        public Object getFieldValue() {
            return _fieldValue;
        }

        public Type getFieldType() {
            return _fieldType;
        }

        public String getClassName() {
            return _className;
        }

        public String buildAssertionString() {
            if (_fieldValue != null && (_fieldType == String.class || _fieldType == Date.class))
                return formatAssertionString("'", "'");
            else
                return formatAssertionString("", "");

        }

        private String formatAssertionString(String valuePrefix, String valueSuffix) {
            return COMMAND_NAME + "   \"" +
                    getClassName() + "." + getFieldName() +
                    " == " +
                    valuePrefix + getFieldValue() + valueSuffix + "\"";
        }
}