package com.uaihebert.uaimockserver.dto.model;

// todo rename to rootConfig...
public class UaiRootConfigurationDTO {
    private Integer port;

    private Boolean fileLog;
    private Boolean consoleLog;

    private String host;
    private String context;
    private String defaultContentType;

    private UaiFileDTO uaiMainFile;

    public UaiRootConfigurationDTO() {
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(final Integer port) {
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
    // todo create a delete button in the log event GUI
    public void setUaiMainFile(final UaiFileDTO uaiFile) {
        this.uaiMainFile = uaiFile;
    }

    public Boolean getFileLog() {
        return fileLog;
    }

    public void setFileLog(final Boolean fileLog) {
        this.fileLog = fileLog;
    }

    public Boolean getConsoleLog() {
        return consoleLog;
    }

    public void setConsoleLog(final Boolean consoleLog) {
        this.consoleLog = consoleLog;
    }
}