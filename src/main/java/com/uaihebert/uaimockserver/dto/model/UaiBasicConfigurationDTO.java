package com.uaihebert.uaimockserver.dto.model;

public class UaiBasicConfigurationDTO {
    private int port;

    private String host;
    private String context;
    private String defaultContentType;

    private UaiFileDTO uaiMainFile;

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

    public String getDefaultContentType() {
        return defaultContentType;
    }

    public void setDefaultContentType(final String defaultContentType) {
        this.defaultContentType = defaultContentType;
    }

    public UaiFileDTO getUaiMainFile() {
        return uaiMainFile;
    }

    public void setMainUaiFile(final UaiFileDTO uaiFile) {
        this.uaiMainFile = uaiFile;
    }
}