package com.unknown.doksan.domain;

import java.util.List;

public class MultipleStandardAndListFieldsObject extends TwoStringOneIntFieldObject {
    private List<String> _stringListFieldA;
    private List<String> _stringListFieldB;

    public List<String> getStringListFieldA() {
        return _stringListFieldA;
    }

    public void setStringListFieldA(List<String> stringListFieldA) {
        _stringListFieldA = stringListFieldA;
    }

    public List<String> getStringListFieldB() {
        return _stringListFieldB;
    }

    public void setStringListFieldB(List<String> stringListFieldB) {
        _stringListFieldB = stringListFieldB;
    }
}