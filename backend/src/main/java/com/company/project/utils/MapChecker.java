package com.company.project.utils;

import java.util.Map;

import static java.util.Objects.nonNull;

public class MapChecker<K, V> {
    private final Map<K,V> checked;

    public MapChecker(Map<K,V> checked) {
        this.checked = checked;
    }

    public boolean notEmpty() {
        return nonNull(checked) && checked.isEmpty();
    }
}