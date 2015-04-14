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

import com.uaihebert.uaimockserver.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * Class that will hold all the response data
 */
public final class UaiResponse {
    private int statusCode;

    private boolean bodyPointingToFile;

    private String body;
    private String bodyPath;
    private String contentType;

    private List<UaiHeader> headerList;

    public UaiResponse() {
    }

    public UaiResponse(final int statusCode, final String body, final String contentType, final List<UaiHeader> headerList, final String bodyPath, final boolean bodyPointingToFile) {
        this.statusCode = statusCode;
        this.body = body;
        this.bodyPath = bodyPath;
        this.contentType = contentType;
        this.headerList = headerList;
        this.bodyPointingToFile = bodyPointingToFile;
    }

    @Override
    public String toString() {
        return "UaiResponse{" +
                "statusCode=" + statusCode +
                ", body='" + body + '\'' +
                ", contentType='" + contentType + '\'' +
                ", headerList=" + headerList +
                '}';
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public String getBodyPath() {
        return bodyPath;
    }

    public String getContentType() {
        return contentType;
    }

    public boolean isBodyPointingToFile() {
        return bodyPointingToFile;
    }

    public List<UaiHeader> getHeaderList() {
        if (headerList == null) {
            headerList = Collections.emptyList();
        }

        return headerList;
    }

    public void configureContentType(final String defaultContentType) {
        if (StringUtils.isBlank(contentType)) {
            contentType = defaultContentType;
        }
    }
}