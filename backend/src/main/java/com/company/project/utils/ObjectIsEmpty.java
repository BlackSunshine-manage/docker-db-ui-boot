package com.company.project.utils;

import java.util.Objects;

public class ObjectIsEmpty<O> extends ObjectNotEmpty<O>{
    public ObjectIsEmpty(O objectToVerification, Action actionWhenCollectionNotEmpty) {
        super(objectToVerification, actionWhenCollectionNotEmpty);
    }

    public ObjectIsEmpty(O objectToVerification) {
        super(objectToVerification);
    }

    @Override
    public boolean test() {
        return !super.test();
    }
}