package com.uaihebert.uaimockserver.model;

import java.util.List;

public class UaiMockServerConfigAsJson {
    private int port;

    private boolean fileLog;
    private boolean consoleLog;

    private String host;
    private String context;
    private String defaultContentType;

    private List<String> mappingRoutesFileList;
    private List<UaiRoute> routes;

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public boolean isFileLog() {
        return fileLog;
    }

    public void setFileLog(final boolean fileLog) {
        this.fileLog = fileLog;
    }

    public boolean isConsoleLog() {
        return consoleLog;
    }

    public void setConsoleLog(final boolean consoleLog) {
        this.consoleLog = consoleLog;
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

    public List<String> getMappingRoutesFileList() {
        return mappingRoutesFileList;
    }

    public void setMappingRoutesFileList(final List<String> mappingRoutesFileList) {
        this.mappingRoutesFileList = mappingRoutesFileList;
    }

    public List<UaiRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(final List<UaiRoute> routes) {
        this.routes = routes;
    }
}