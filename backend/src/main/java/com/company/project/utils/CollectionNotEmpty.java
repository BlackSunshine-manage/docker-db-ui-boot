package com.company.project.utils;

import java.util.Collection;

import static java.util.Objects.nonNull;

public class CollectionNotEmpty<I, C extends Collection<I>> implements Predicative {
    private final C collectionToVerification;

    public CollectionNotEmpty(C collectionToVerification, Action actionWhenCollectionNotEmpty) {
        this.collectionToVerification = collectionToVerification;
        if (test()) actionWhenCollectionNotEmpty.action();
    }

    public boolean test() {
        return nonNull(collectionToVerification) && !collectionToVerification.isEmpty();
    }
}