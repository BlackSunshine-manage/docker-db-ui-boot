package com.company.project.utils;

import java.util.Collection;

import static java.util.Objects.nonNull;

public class CollectionChecker<I> {
    private final Collection<I> collection;

    public CollectionChecker(Collection<I> collection) {
        this.collection = collection;
    }

    public boolean notEmpty() {
        return nonNull(collection) && collection.isEmpty();
    }
}