package com.company.project.utils;

import java.util.Collection;
import java.util.function.Predicate;

public class CollectionAnyMatchByPredicate<I, C extends Collection<I>> {
    private final C collectionToVerification;
    private final Predicate<I> filteredBy;

    public CollectionAnyMatchByPredicate(C collectionToVerification,
                                         Predicate<I> filteredBy,
                                         Action actionWhenCollectionNotEmpty) {
        this.collectionToVerification = collectionToVerification;
        this.filteredBy = filteredBy;
        if (test()) actionWhenCollectionNotEmpty.action();
    }

    public boolean test() {
        return collectionToVerification.stream()
                .anyMatch(filteredBy);
    }
}