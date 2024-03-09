package com.company.project.utils;

import java.util.Collection;

public class CollectionsNotEquivalence<I, C extends Collection<I>> implements Predicative {
    private final C firstCollectionToVerification;
    private final C secondCollectionToVerification;

    public CollectionsNotEquivalence(C firstCollectionToVerification,
                                     C secondCollectionToVerification,
                                     Action actionWhenCollectionsNotEquivalence) {
        this.firstCollectionToVerification = firstCollectionToVerification;
        this.secondCollectionToVerification = secondCollectionToVerification;
        final Action checkSecondCollection = () -> new CollectionNotEmpty<>(secondCollectionToVerification, () -> {
            if (test()) actionWhenCollectionsNotEquivalence.action();
        });
        new CollectionNotEmpty<>(firstCollectionToVerification, checkSecondCollection);
    }

    public boolean test() {
        return !firstCollectionToVerification.equals(secondCollectionToVerification);
    }
}