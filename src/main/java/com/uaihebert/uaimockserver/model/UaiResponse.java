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

import java.util.List;

/**
 * Class that will hold all the response data
 */
public final class UaiResponse {
    public final int statusCode;

    public final String body;
    public final String contentType;

    public final List<UaiHeader> headerList;

    public UaiResponse(final int statusCode, final String body, final String contentType, final List<UaiHeader> headerList) {
        this.statusCode = statusCode;
        this.body = body;
        this.contentType = contentType;
        this.headerList = headerList;
    }

    @Override
    public String toString() {
        return "UaiResponse{" +
                "statusCode=" + statusCode +
                ", body='" + body + '\'' +
                ", requiredContentType='" + contentType + '\'' +
                ", headerList=" + headerList +
                '}';
    }
}