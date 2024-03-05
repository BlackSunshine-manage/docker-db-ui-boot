package com.company.project.utils;

import java.util.Map;

import static java.util.Objects.nonNull;

public class MapNotEmpty<K, V, M extends Map<K, V>> implements Predicative {
    private final M mapToVerification;

    public MapNotEmpty(M mapToVerification, Action actionWhenCollectionNotEmpty) {
        this.mapToVerification = mapToVerification;
        if (test()) actionWhenCollectionNotEmpty.action();
    }

    public boolean test() {
        return nonNull(mapToVerification) && mapToVerification.isEmpty();
    }
}