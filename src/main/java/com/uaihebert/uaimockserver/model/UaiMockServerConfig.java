/*
 * Copyright 2015 uaiHebert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
package com.uaihebert.uaimockserver.model;

import java.util.Collections;
import java.util.List;

/**
 * Class that will hold all the project configurations
 */
public final class UaiMockServerConfig {
    private int port;

    private boolean fileLog;
    private boolean consoleLog;

    private String host;
    private String context;
    private String defaultContentTypeResponse;

    private List<UaiRoute> routeList;
    private List<String> mappingRoutesFileList;

    private UaiFile uaiFile;

    public UaiMockServerConfig() {
    }


    public void postConstruct() {
        for (UaiRoute uaiRoute : getRouteList()) {
            uaiRoute.setUaiFile(uaiFile);
            uaiRoute.createId();
            uaiRoute.getResponse().configureContentType(defaultContentTypeResponse);
        }
    }

    public void setUaiFile(final UaiFile uaiFile) {
        this.uaiFile = uaiFile;
    }

    public int getPort() {
        return port;
    }

    public boolean isFileLog() {
        return fileLog;
    }

    public boolean isConsoleLog() {
        return consoleLog;
    }

    public String getHost() {
        return host;
    }

    public String getContext() {
        return context;
    }

    public String getDefaultContentTypeResponse() {
        return defaultContentTypeResponse;
    }

    public List<UaiRoute> getRouteList() {
        if (routeList == null) {
            routeList = Collections.emptyList();
        }

        return routeList;
    }

    public List<String> getMappingRoutesFileList() {
        if (mappingRoutesFileList == null) {
            mappingRoutesFileList = Collections.emptyList();
        }

        return mappingRoutesFileList;
    }

    public UaiFile getUaiFile() {
        return uaiFile;
    }
}