package com.unknown.heo;




import com.unknown.heo.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GeneratorTest {

    private Generator _generator = new Generator();

    @Test
    public void assertionsForObjectWithNoFields() throws Exception {
        NoFieldsObject testObject = new NoFieldsObject();
        List<String> assertions = _generator.buildAssertions(testObject);
        assertEquals(0, assertions.size());
    }

    @Test
    public void assertionsForObjectWithOneStringField() throws Exception {
        String stringFieldA = GeneratorTestHelper.getRandomString();
        OneStringFieldObject testObject = GeneratorTestHelper.buildOneStringFieldTestObject(stringFieldA);

        List<String> assertions = _generator.buildAssertions(testObject);
        assertEquals(1, assertions.size());

        String assertion = assertions.get(0);
        assertEquals(GeneratorTestHelper.buildAssertionString("oneStringFieldObject.stringFieldA", stringFieldA), assertion);
    }

    @Test
    public void assertionsForObjectWithTwoStringFields() throws Exception {
        String stringFieldA = GeneratorTestHelper.getRandomString();
        String stringFieldB = GeneratorTestHelper.getRandomString();

        TwoStringFieldObject testObject = GeneratorTestHelper.buildTwoStringFieldTestObject(stringFieldA, stringFieldB);

        List<String> assertions = _generator.buildAssertions(testObject);

        assertEquals(2, assertions.size());
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("twoStringFieldObject.stringFieldA", stringFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("twoStringFieldObject.stringFieldB", stringFieldB)));
    }

    @Test
    public void assertionsForObjectWithTwoStringOneIntFields() throws Exception {
        String stringFieldA = GeneratorTestHelper.getRandomString();
        String stringFieldB = GeneratorTestHelper.getRandomString();
        int intFieldA = GeneratorTestHelper.getRandomInt();

        TwoStringOneIntFieldObject testObject = GeneratorTestHelper.buildTwoStringOneIntFieldTestObject(stringFieldA, stringFieldB, intFieldA);

        List<String> assertions = _generator.buildAssertions(testObject);

        assertEquals(3, assertions.size());
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("twoStringOneIntFieldObject.stringFieldA", stringFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("twoStringOneIntFieldObject.stringFieldB", stringFieldB)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("twoStringOneIntFieldObject.intFieldA", intFieldA)));
    }

    @Test
    public void assertionsForObjectWithMultipleStandardFields() throws Exception {
        String stringFieldA = GeneratorTestHelper.getRandomString();
        String stringFieldB = GeneratorTestHelper.getRandomString();
        int intFieldA = GeneratorTestHelper.getRandomInt();
        int intFieldB = GeneratorTestHelper.getRandomInt();
        long longFieldA = GeneratorTestHelper.getRandomInt();
        Date dateFieldA = new Date();

        MultipleStandardFieldsObject testObject = GeneratorTestHelper.buildMultipleStandardFieldsObject(stringFieldA, stringFieldB, intFieldA, intFieldB, longFieldA, dateFieldA);

        List<String> assertions = _generator.buildAssertions(testObject);

        assertEquals(6, assertions.size());
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardFieldsObject.stringFieldA", stringFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardFieldsObject.stringFieldB", stringFieldB)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardFieldsObject.intFieldA", intFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardFieldsObject.intFieldB", intFieldB)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardFieldsObject.longFieldA", longFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardFieldsObject.dateFieldA", dateFieldA)));
    }

    @Test
    public void assertionsForObjectExtendingAnotherObject() throws Exception {
        String stringFieldA = GeneratorTestHelper.getRandomString();
        String stringFieldB = GeneratorTestHelper.getRandomString();
        String stringFieldC = GeneratorTestHelper.getRandomString();
        String stringFieldD = GeneratorTestHelper.getRandomString();
        int intFieldA = GeneratorTestHelper.getRandomInt();
        int intFieldC = GeneratorTestHelper.getRandomInt();

        ObjectExtendingAnotherObject testObject = GeneratorTestHelper.buildObjectExtendingAnotherObject(stringFieldA, stringFieldB, stringFieldC, stringFieldD, intFieldA, intFieldC);

        List<String> assertions = _generator.buildAssertions(testObject);

        assertEquals(6, assertions.size());
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectExtendingAnotherObject.stringFieldA", stringFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectExtendingAnotherObject.stringFieldB", stringFieldB)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectExtendingAnotherObject.stringFieldC", stringFieldC)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectExtendingAnotherObject.stringFieldD", stringFieldD)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectExtendingAnotherObject.intFieldA", intFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectExtendingAnotherObject.intFieldC", intFieldC)));
    }

    @Test
    public void assertionsForObjectContainingStringList() throws Exception {
        String stringItemA = GeneratorTestHelper.getRandomString();
        String stringItemB = GeneratorTestHelper.getRandomString();
        String stringItemC = GeneratorTestHelper.getRandomString();

        OneStringListObject testObject = GeneratorTestHelper.buildOneStringListFieldObject(stringItemA, stringItemB, stringItemC);

        List<String> assertions = _generator.buildAssertions(testObject);

        assertEquals(3, assertions.size());
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("oneStringListObject.stringListFieldA[0]", stringItemA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("oneStringListObject.stringListFieldA[1]", stringItemB)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("oneStringListObject.stringListFieldA[2]", stringItemC)));
    }

    @Test
    public void assertionsForObjectExtendingAnotherObjectWithTwoStringLists() throws Exception {
        String stringFieldA = GeneratorTestHelper.getRandomString();
        String stringFieldB = GeneratorTestHelper.getRandomString();
        int intFieldA = GeneratorTestHelper.getRandomInt();
        String listFieldAStringA = GeneratorTestHelper.getRandomString();
        String listFieldAStringB = GeneratorTestHelper.getRandomString();
        String listFieldAStringC = GeneratorTestHelper.getRandomString();
        String listFieldBStringA = GeneratorTestHelper.getRandomString();
        String listFieldBStringB = GeneratorTestHelper.getRandomString();

        ArrayList<String> listFieldA = new ArrayList<String>();
        listFieldA.add(listFieldAStringA);
        listFieldA.add(listFieldAStringB);
        listFieldA.add(listFieldAStringC);

        ArrayList<String> listFieldB = new ArrayList<String>();
        listFieldB.add(listFieldBStringA);
        listFieldB.add(listFieldBStringB);

        MultipleStandardAndListFieldsObject testObject = GeneratorTestHelper.buildMultipleStandardAndListFieldsObject(stringFieldA, stringFieldB, intFieldA, listFieldA, listFieldB);
        List<String> assertions = _generator.buildAssertions(testObject);

        assertEquals(8, assertions.size());
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardAndListFieldsObject.stringFieldA", stringFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardAndListFieldsObject.stringFieldB", stringFieldB)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardAndListFieldsObject.intFieldA", intFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardAndListFieldsObject.stringListFieldA[0]", listFieldAStringA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardAndListFieldsObject.stringListFieldA[1]", listFieldAStringB)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardAndListFieldsObject.stringListFieldA[2]", listFieldAStringC)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardAndListFieldsObject.stringListFieldB[0]", listFieldBStringA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("multipleStandardAndListFieldsObject.stringListFieldB[1]", listFieldBStringB)));
    }

    @Test
    public void assertionsForObjectContainingOneObjectAndMiscFields() throws Exception {
        String objectAstringFieldA = GeneratorTestHelper.getRandomString();
        String objectBStringFieldA = GeneratorTestHelper.getRandomString();
        String objectBStringFieldB = GeneratorTestHelper.getRandomString();
        int objectBIntFieldA = GeneratorTestHelper.getRandomInt();

        ObjectContainingOneObjectAndMiscFields testObject = GeneratorTestHelper.buildObjectContainingOneObjectAndMiscFields(objectAstringFieldA, objectBStringFieldA, objectBStringFieldB, objectBIntFieldA);

        _generator.addClass(OneStringFieldObject.class);
        List<String> assertions = _generator.buildAssertions(testObject);
        assertEquals(4, assertions.size());

        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectContainingOneObjectAndMiscFields.oneStringFieldObject.stringFieldA", objectAstringFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectContainingOneObjectAndMiscFields.stringFieldA", objectBStringFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectContainingOneObjectAndMiscFields.stringFieldB", objectBStringFieldB)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectContainingOneObjectAndMiscFields.intFieldA", objectBIntFieldA)));
    }

    @Test
    public void assertionsForObjectContainingTwoDifferentObjects() throws Exception {
        String objectAstringFieldA = GeneratorTestHelper.getRandomString();
        String objectBStringFieldA = GeneratorTestHelper.getRandomString();
        String objectBStringFieldB = GeneratorTestHelper.getRandomString();
        int objectBIntFieldA = GeneratorTestHelper.getRandomInt();

        ObjectContainingTwoDifferentObjects testObject = GeneratorTestHelper.buildObjectContainingTwoDifferentObjects(objectAstringFieldA, objectBStringFieldA, objectBStringFieldB, objectBIntFieldA);

        _generator.addClass(OneStringFieldObject.class);
        _generator.addClass(TwoStringOneIntFieldObject.class);
        List<String> assertions = _generator.buildAssertions(testObject);

        assertEquals(4, assertions.size());
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectContainingTwoDifferentObjects.oneStringFieldObject.stringFieldA", objectAstringFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectContainingTwoDifferentObjects.twoStringOneIntFieldObject.stringFieldA", objectBStringFieldA)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectContainingTwoDifferentObjects.twoStringOneIntFieldObject.stringFieldB", objectBStringFieldB)));
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectContainingTwoDifferentObjects.twoStringOneIntFieldObject.intFieldA", objectBIntFieldA)));
    }

    @Test
    public void assertionsForObjectContainingObjectWhichContainsObject() throws Exception {
        String objectAstringFieldA = GeneratorTestHelper.getRandomString();

        ObjectContainingObjectContainingObject testObject = GeneratorTestHelper.buildObjectContainingObjectContainingObject(objectAstringFieldA);

        _generator.addClass(ObjectContainingObject.class);
        _generator.addClass(OneStringFieldObject.class);
        List<String> assertions = _generator.buildAssertions(testObject);

        assertEquals(1, assertions.size());
        assertTrue(assertions.contains(GeneratorTestHelper.buildAssertionString("objectContainingObjectContainingObject.objectContainingObject.oneStringFieldObject.stringFieldA", objectAstringFieldA)));
    }

    @Test
    public void canHandleObjectsContainingNullFields() throws Exception {
        List<String> assertions = _generator.buildAssertions(new OneStringFieldObject());
        assertEquals(1, assertions.size());
        assertEquals(GeneratorTestHelper.buildAssertionString("oneStringFieldObject.stringFieldA", (String) null), assertions.get(0));
    }

    @Test
    public void canHandleDateFields() throws Exception {
        List<String> assertions = _generator.buildAssertions(
                new Object() {
                    Date _testDate = new Date(5555L);

                    public Date getTestDate() {
                        return _testDate;
                    }
                }
        );
        assertEquals(1, assertions.size());
        assertEquals("AssertExpression   \".testDate == 'Thu Jan 01 01:00:05 GMT 1970'\"", assertions.get(0));
    }


    // is and get for boolean getters

    // root object is  a list

    // root object is a realistic sns resource type object

//Run all inspections
//Run unit coverage
//replace test domain object builders in helper class with constructors?
//handle more than one superclass up
//handle lists in lists and different types of lists, maps etc.?
//handle arrays
//handle list as root object
//try to extract out class walking functionality as generic-nothing-to-do-with-assertion-generation "reflector" and have the main code use it as a tool for building assertions
//remove dependency in production code on test domain objects if there are any
//booleans that are is instead of get (scan for both method names?)
//scan for non-void return type methods instead of get*
    // make sure all methods are selects, sequences, etc.
}