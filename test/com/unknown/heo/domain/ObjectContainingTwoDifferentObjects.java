package com.unknown.heo.domain;

public class ObjectContainingTwoDifferentObjects {
    private OneStringFieldObject _oneStringFieldObject;
    private TwoStringOneIntFieldObject _twoStringOneIntFieldObject;

    public OneStringFieldObject getOneStringFieldObject() {
        return _oneStringFieldObject;
    }

    public void setOneStringFieldObject(OneStringFieldObject oneStringFieldObject) {
        _oneStringFieldObject = oneStringFieldObject;
    }

    public TwoStringOneIntFieldObject getTwoStringOneIntFieldObject() {
        return _twoStringOneIntFieldObject;
    }

    public void setTwoStringOneIntFieldObject(TwoStringOneIntFieldObject twoStringOneIntFieldObject) {
        _twoStringOneIntFieldObject = twoStringOneIntFieldObject;
    }
}