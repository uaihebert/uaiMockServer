package com.uaihebert.uaimockserver.dto.model;

public class UaiBasicConfigurationDTO {
    private int port;

    private String host;
    private String context;
    private String mainConfigFilePath;
    private String defaultContentType;

    public UaiBasicConfigurationDTO() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public String getContext() {
        return context;
    }

    public void setContext(final String context) {
        this.context = context;
    }

    public String getMainConfigFilePath() {
        return mainConfigFilePath;
    }

    public void setMainConfigFilePath(final String mainConfigFilePath) {
        this.mainConfigFilePath = mainConfigFilePath;
    }

    public String getDefaultContentType() {
        return defaultContentType;
    }

    public void setDefaultContentType(final String defaultContentType) {
        this.defaultContentType = defaultContentType;
    }
}