package com.uaihebert.uaimockserver.configuration;

public enum ProjectConfiguration {
    ENCODING();

    public final String value;

    ProjectConfiguration() {
        this.value = "UTF-8";
    }
}
