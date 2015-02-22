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
package com.uaihebert.uaimockserver.constants;

/**
 * Constants with the request attribute names
 */
public enum RequestConstants {
    NAME("name"),
    PATH("path"),
    METHOD("method"),
    DESCRIPTION("description"),
    CONTENT_TYPE("requiredContentType"),
    IS_BODY_REQUIRED("isBodyRequired"),
    REQUIRED_HEADER_LIST("requiredHeaderList"),
    HOLD_THE_REQUEST_IN_MILLI("holdTheRequestInMilli"),
    REQUIRED_QUERY_PARAM_LIST("requiredQueryParamList");

    private static final String REQUEST_PATH = "request.";

    public final String path;

    private RequestConstants(final String path) {
        this.path = REQUEST_PATH + path;
    }
}