package com.ca.security.roles.demo78.filtering;

public enum DoneFilterOption {
    ALL("All"),
    PLAN("Plan"),
    DONE("Done");

    private final String description;

    DoneFilterOption(String description) {
        this.description = description;
    }
}
