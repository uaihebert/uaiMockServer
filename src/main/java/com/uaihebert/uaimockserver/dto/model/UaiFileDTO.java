package com.uaihebert.uaimockserver.dto.model;

public class UaiFileDTO {
    private String name;
    private String fullPath;

    public UaiFileDTO(final String name, final String fullPath) {
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
