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

import com.uaihebert.uaimockserver.constants.UaiHttpMethod;

import java.util.ArrayList;
import java.util.List;

public class UaiCallback {
    public static final long TIMEOUT_IN_MILLIS = 5000L;
    private static final int DEFAULT_DELAY_IN_MILLIS = 500;

    private int delayInMilli = DEFAULT_DELAY_IN_MILLIS;

    private String bodyToSend;
    private String completeUrlToCall;

    private UaiHttpMethod httpMethod;

    private List<UaiHeader> headerList;
    private List<UaiQueryParam> queryParamList;

    public String getBodyToSend() {
        return bodyToSend;
    }

    public void setBodyToSend(final String bodyToSend) {
        this.bodyToSend = bodyToSend;
    }

    public String getCompleteUrlToCall() {
        return completeUrlToCall;
    }

    public void setCompleteUrlToCall(final String completeUrlToCall) {
        this.completeUrlToCall = completeUrlToCall;
    }

    public List<UaiHeader> getHeaderList() {
        if (headerList == null) {
            headerList = new ArrayList<UaiHeader>();
        }

        return headerList;
    }

    public List<UaiQueryParam> getQueryParamList() {
        if (queryParamList == null) {
            queryParamList = new ArrayList<UaiQueryParam>();
        }

        return queryParamList;
    }

    public int getDelayInMilli() {
        return delayInMilli;
    }

    public void setDelayInMilli(final int delayInMilli) {
        this.delayInMilli = delayInMilli;
    }

    public UaiHttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(final UaiHttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setHeaderList(final List<UaiHeader> headerList) {
        this.headerList = headerList;
    }

    public void setQueryParamList(final List<UaiQueryParam> queryParamList) {
        this.queryParamList = queryParamList;
    }
}
