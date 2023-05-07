package com.ca.security.roles.demo78.filtering;

public class FilterAdjuster {

    private String searchText;

    private DoneFilterOption done;

    public String getSearchText() {
        return searchText;
    }

    public DoneFilterOption getDone() {
        return done;
    }

    public boolean isAll() {
        return (searchText == null || searchText.isEmpty()) &&
                (done == null || done == DoneFilterOption.ALL);
    }
}
