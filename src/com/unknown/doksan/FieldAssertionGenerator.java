package com.unknown.doksan;

import java.util.ArrayList;
import java.util.List;

public class FieldAssertionGenerator {
    public static List<String> buildAssertions(Object object, List<Class> classes) throws Exception {
        if (classes == null)
            classes = new ArrayList<Class>();
        return buildAssertionStrings(FieldUtils.buildAssertionObjects2(object, classes));
    }

    private static List<String> buildAssertionStrings(List<Assertion> assertions) {
        ArrayList<String> assertionStrings = new ArrayList<String>();
        for (Assertion assertion : assertions)
            assertionStrings.add(assertion.buildAssertionString());
        return assertionStrings;
    }
}