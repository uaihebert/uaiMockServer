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
 * Class that will hold all the request data
 */
public final class UaiRequest {
    private String name;
    private String path;
    private String method;
    private String description;
    private String requiredContentType;

    private Long holdTheRequestInMilli;

    private Boolean isBodyRequired;

    private List<UaiHeader> requiredHeaderList;
    private List<UaiQueryParam> requiredQueryParamList;

    public UaiRequest() {
    }

    public UaiRequest(final String name, final String path, final String method, final String description, final String requiredContentType, final Long holdTheRequestInMilli, final Boolean isBodyRequired, final List<UaiHeader> requiredHeaderList, final List<UaiQueryParam> requiredQueryParamList) {
        this.name = name;
        this.path = path;
        this.method = method;
        this.description = description;
        this.requiredContentType = requiredContentType;
        this.holdTheRequestInMilli = holdTheRequestInMilli;
        this.isBodyRequired = isBodyRequired;
        this.requiredHeaderList = requiredHeaderList;
        this.requiredQueryParamList = requiredQueryParamList;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public String getDescription() {
        return description;
    }

    public String getRequiredContentType() {
        return requiredContentType;
    }

    public Long getHoldTheRequestInMilli() {
        return holdTheRequestInMilli;
    }

    public Boolean isBodyRequired() {
        return isBodyRequired;
    }

    public List<UaiHeader> getRequiredHeaderList() {
        if (requiredHeaderList == null) {
            requiredHeaderList = Collections.emptyList();
        }

        return requiredHeaderList;
    }

    public List<UaiQueryParam> getRequiredQueryParamList() {
        if (requiredQueryParamList == null) {
            requiredQueryParamList = Collections.emptyList();
        }

        return requiredQueryParamList;
    }
}