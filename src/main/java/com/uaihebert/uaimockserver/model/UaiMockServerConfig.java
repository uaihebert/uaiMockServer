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

import com.uaihebert.uaimockserver.constants.ValidatorConstants;

import java.util.ArrayList;
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
            uaiRoute.createId();
            uaiRoute.setUaiFile(uaiFile);

            configureRoutes(uaiRoute);
        }

        // the default GSON List implementation has no add method implemented
        routeList = new ArrayList<UaiRoute>(routeList);
    }

    private void configureRoutes(final UaiRoute uaiRoute) {
        uaiRoute.getResponse().configureContentType(defaultContentTypeResponse);

        defineIfRequestHeaderUsingWildCard(uaiRoute);
        defineIfRequestQueryParamUsingWildCard(uaiRoute);
    }

    private void defineIfRequestQueryParamUsingWildCard(final UaiRoute uaiRoute) {
        for (UaiQueryParam uaiQueryParam : uaiRoute.getRequest().getRequiredQueryParamList()) {
            final boolean usingWildCard = uaiQueryParam.getValueList().contains(ValidatorConstants.VALID_WILD_CARD.text);
            uaiQueryParam.setUsingWildCard(usingWildCard);
        }
    }

    private void defineIfRequestHeaderUsingWildCard(final UaiRoute uaiRoute) {
        for (UaiHeader uaiHeader : uaiRoute.getRequest().getRequiredHeaderList()) {
            final boolean usingWildCard = uaiHeader.getValueList().contains(ValidatorConstants.VALID_WILD_CARD.text);
            uaiHeader.setUsingWildCard(usingWildCard);
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