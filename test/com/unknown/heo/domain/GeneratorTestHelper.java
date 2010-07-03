package com.unknown.heo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.random;

public class GeneratorTestHelper {

    public static String buildAssertionString(String fieldName, String fieldValue) {
        if (fieldValue != null)
            return "AssertExpression   \"" + fieldName + " == '" + fieldValue + "'\"";
        else
            return "AssertExpression   \"" + fieldName + " == null\"";
    }

    public static String buildAssertionString(String fieldName, int fieldValue) {
        return "AssertExpression   \"" + fieldName + " == " + fieldValue + "\"";
    }

    public static String buildAssertionString(String fieldName, long fieldValue) {
        return "AssertExpression   \"" + fieldName + " == " + fieldValue + "\"";
    }

    public static String buildAssertionString(String fieldName, Date fieldValue) {
        if (fieldValue != null)
            return "AssertExpression   \"" + fieldName + " == '" + fieldValue + "'\"";
        else
            return "AssertExpression   \"" + fieldName + " == null";
    }

    public static OneStringFieldObject buildOneStringFieldTestObject(String stringValueA) {
        OneStringFieldObject testObject = new OneStringFieldObject();
        testObject.setStringFieldA(stringValueA);
        return testObject;
    }

    public static TwoStringFieldObject buildTwoStringFieldTestObject(String stringValueA, String stringValueB) {
        TwoStringFieldObject testObject = new TwoStringFieldObject();
        testObject.setStringFieldA(stringValueA);
        testObject.setStringFieldB(stringValueB);
        return testObject;
    }

    public static TwoStringOneIntFieldObject buildTwoStringOneIntFieldTestObject(String stringValueA, String stringValueB, int intValueA) {
        TwoStringOneIntFieldObject testObject = new TwoStringOneIntFieldObject();
        testObject.setStringFieldA(stringValueA);
        testObject.setStringFieldB(stringValueB);
        testObject.setIntFieldA(intValueA);
        return testObject;
    }

    public static MultipleStandardFieldsObject buildMultipleStandardFieldsObject(String stringValueA, String stringValueB, int intValueA, int intValueB, long longValueA, Date dateValueA) {
        MultipleStandardFieldsObject testObject = new MultipleStandardFieldsObject();
        testObject.setStringFieldA(stringValueA);
        testObject.setStringFieldB(stringValueB);
        testObject.setIntFieldA(intValueA);
        testObject.setIntFieldB(intValueB);
        testObject.setLongFieldA(longValueA);
        testObject.setDateFieldA(dateValueA);
        return testObject;
    }

    public static ObjectExtendingAnotherObject buildObjectExtendingAnotherObject(String stringValueA, String stringValueB, String stringValueC, String stringValueD, int intValueA, int intValueC) {
        ObjectExtendingAnotherObject testObject = new ObjectExtendingAnotherObject();
        testObject.setStringFieldA(stringValueA);
        testObject.setStringFieldB(stringValueB);
        testObject.setStringFieldC(stringValueC);
        testObject.setStringFieldD(stringValueD);
        testObject.setIntFieldA(intValueA);
        testObject.setIntFieldC(intValueC);
        return testObject;
    }

    public static OneStringListObject buildOneStringListFieldObject(String stringItemA, String stringItemB, String stringItemC) {
        ArrayList<String> listA = new ArrayList<String>();
        listA.add(stringItemA);
        listA.add(stringItemB);
        listA.add(stringItemC);
        OneStringListObject testObject = new OneStringListObject();
        testObject.setStringListFieldA(listA);
        return testObject;
    }

    public static MultipleStandardAndListFieldsObject buildMultipleStandardAndListFieldsObject(String stringValueA, String stringValueB, int intValueA, List<String> listA, List<String> listB) {
        MultipleStandardAndListFieldsObject testObject = new MultipleStandardAndListFieldsObject();
        testObject.setStringFieldA(stringValueA);
        testObject.setStringFieldB(stringValueB);
        testObject.setIntFieldA(intValueA);
        testObject.setStringListFieldA(listA);
        testObject.setStringListFieldB(listB);
        return testObject;
    }

    public static String getRandomString() {
        return String.valueOf(System.currentTimeMillis() + random());
    }

    public static int getRandomInt() {
        return (int) (random()*10000);
    }

    public static ObjectContainingOneObjectAndMiscFields buildObjectContainingOneObjectAndMiscFields(String objectAstringFieldA, String objectBStringFieldA, String objectBStringFieldB, int objectBIntFieldA) {
        ObjectContainingOneObjectAndMiscFields testObject = new ObjectContainingOneObjectAndMiscFields();
        OneStringFieldObject oneStringFieldObject = buildOneStringFieldTestObject(objectAstringFieldA);
        testObject.setOneStringFieldObject(oneStringFieldObject);
        testObject.setStringFieldA(objectBStringFieldA);
        testObject.setStringFieldB(objectBStringFieldB);
        testObject.setIntFieldA(objectBIntFieldA);
        return testObject;
    }

    public static ObjectContainingTwoDifferentObjects buildObjectContainingTwoDifferentObjects(String objectAstringFieldA, String objectBStringFieldA, String objectBStringFieldB, int objectBIntFieldA) {
        ObjectContainingTwoDifferentObjects testObject = new ObjectContainingTwoDifferentObjects();
        OneStringFieldObject oneStringFieldObject = buildOneStringFieldTestObject(objectAstringFieldA);
        TwoStringOneIntFieldObject twoStringOneIntFieldObject = buildTwoStringOneIntFieldTestObject(objectBStringFieldA, objectBStringFieldB, objectBIntFieldA);
        testObject.setOneStringFieldObject(oneStringFieldObject);
        testObject.setTwoStringOneIntFieldObject(twoStringOneIntFieldObject);
        return testObject;
    }


    public static ObjectContainingObjectContainingObject buildObjectContainingObjectContainingObject(String objectAstringFieldA) {
        ObjectContainingObjectContainingObject testObject = new ObjectContainingObjectContainingObject();
        ObjectContainingObject objectContainingObject = new ObjectContainingObject();
        OneStringFieldObject oneStringFieldObject = buildOneStringFieldTestObject(objectAstringFieldA);
        objectContainingObject.setStringFieldObject(oneStringFieldObject);
        testObject.setObjectContainingObject(objectContainingObject);
        return testObject;
    }
}