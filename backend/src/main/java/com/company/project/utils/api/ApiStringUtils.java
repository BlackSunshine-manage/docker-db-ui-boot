package com.company.project.utils.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiStringUtils {
    API("api"),
    BACK_SLASH("/"),
    V1("v1"),
    V2("v2");

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}