package com.unknown.heo.domain;

public class ObjectContainingOneObjectAndMiscFields {
    private OneStringFieldObject _oneStringFieldObject;
    private String _stringFieldA;
    private String _stringFieldB;
    private int _intFieldA;

    public OneStringFieldObject getOneStringFieldObject() {
        return _oneStringFieldObject;
    }

    public void setOneStringFieldObject(OneStringFieldObject oneStringFieldObject) {
        _oneStringFieldObject = oneStringFieldObject;
    }

    public String getStringFieldA() {
        return _stringFieldA;
    }

    public void setStringFieldA(String stringFieldA) {
        _stringFieldA = stringFieldA;
    }

    public String getStringFieldB() {
        return _stringFieldB;
    }

    public void setStringFieldB(String stringFieldB) {
        _stringFieldB = stringFieldB;
    }

    public int getIntFieldA() {
        return _intFieldA;
    }

    public void setIntFieldA(int intFieldA) {
        _intFieldA = intFieldA;
    }
}