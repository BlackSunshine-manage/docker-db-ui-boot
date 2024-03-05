package com.company.project.utils;

import static java.util.Objects.nonNull;

public class ObjectNotEmpty <O> implements Predicative {

    private final O objectToVerification;

    public ObjectNotEmpty(O objectToVerification, Action actionWhenCollectionNotEmpty) {
        this.objectToVerification = objectToVerification;
        if (test()) actionWhenCollectionNotEmpty.action();
    }

    public boolean test() {
        return nonNull(objectToVerification);
    }
}