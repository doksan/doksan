package com.unknown.heo.domain;

public class ObjectExtendingAnotherObject extends TwoStringOneIntFieldObject {
    private String _stringFieldC;
    private String _stringFieldD;
    private int _intFieldC;

    public String getStringFieldC() {
        return _stringFieldC;
    }

    public void setStringFieldC(String stringFieldC) {
        _stringFieldC = stringFieldC;
    }

    public String getStringFieldD() {
        return _stringFieldD;
    }

    public void setStringFieldD(String stringFieldD) {
        _stringFieldD = stringFieldD;
    }

    public int getIntFieldC() {
        return _intFieldC;
    }

    public void setIntFieldC(int intFieldC) {
        _intFieldC = intFieldC;
    }
}