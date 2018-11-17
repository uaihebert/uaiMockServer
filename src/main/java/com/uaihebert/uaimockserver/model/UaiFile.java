package com.uaihebert.uaimockserver.model;

public class UaiFile {
    private final String name;
    private final String fullPath;

    public UaiFile(final String name, final String fullPath) {
        this.name = name;
        this.fullPath = fullPath;
    }

    public String getName() {
        return name;
    }

    public String getFullPath() {
        return fullPath;
    }
}
