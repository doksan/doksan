package com.unknown.doksan;

import java.lang.reflect.Type;
import java.util.Date;

public class Field {
    private String _name;
    private Object _value;
    private Type _type;
    private String _className;

    Field(String className, String name, Object value, Type type) {
        _className = className;
        _name = name;
        _value = value;
        _type = type;
    }

    public String getName() {
        return _name;
    }

    public Object getValue() {
        return _value;
    }

    public Type getType() {
        return _type;
    }

    public String getClassName() {
        return _className;
    }
}