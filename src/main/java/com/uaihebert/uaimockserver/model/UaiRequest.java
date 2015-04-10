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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that will hold all the request data
 */
public final class UaiRequest {
    private List<UaiHeader> optionalHeaderList;

    private List<UaiHeader> requiredHeaderList;
    private List<UaiQueryParam> optionalQueryParamList;

    private List<UaiQueryParam> requiredQueryParamList;

    public final String name;
    public final String path;
    public final String body;
    public final String method;
    public final String description;
    public final String requiredContentType;

    public final Long holdTheRequestInMilli;

    public final Boolean isBodyRequired;

    public final BodyValidationType bodyValidationType;

    private UaiRequest(final UaiRequestBuilder builder) {
        this.name = builder.name;
        this.path = builder.path;
        this.body = builder.body;
        this.method = builder.method;
        this.description = builder.description;
        this.requiredContentType = builder.requiredContentType;

        this.holdTheRequestInMilli = builder.holdTheRequestInMilli;

        this.isBodyRequired = builder.isBodyRequired;

        this.requiredHeaderList = builder.requiredHeaderList;
        this.requiredQueryParamList = builder.requiredQueryParamList;

        this.optionalHeaderList = builder.optionalHeaderList;
        this.optionalQueryParamList = builder.optionalQueryParamList;

        this.bodyValidationType = builder.bodyValidationType;
    }

    public List<UaiHeader> getRequiredHeaderList() {
        if (requiredHeaderList == null) {
            requiredHeaderList = Collections.emptyList();
        }

        return requiredHeaderList;
    }

    public List<UaiHeader> getAllHeadersList() {
        List<UaiHeader> uaiHeaderList = new ArrayList<UaiHeader>();

        uaiHeaderList.addAll(getOptionalHeaderList());
        uaiHeaderList.addAll(getRequiredHeaderList());

        return uaiHeaderList;
    }

    public List<UaiQueryParam> getRequiredQueryParamList() {
        if (requiredQueryParamList == null) {
            requiredQueryParamList = Collections.emptyList();
        }

        return requiredQueryParamList;
    }

    public List<UaiQueryParam> getAllQueryParam() {
        final List<UaiQueryParam> uaiQueryParamList = new ArrayList<UaiQueryParam>();

        uaiQueryParamList.addAll(getOptionalQueryParamList());
        uaiQueryParamList.addAll(getRequiredQueryParamList());

        return uaiQueryParamList;
    }

    public List<UaiHeader> getOptionalHeaderList() {
        if (optionalHeaderList == null) {
            optionalHeaderList = Collections.emptyList();
        }

        return optionalHeaderList;
    }

    public List<UaiQueryParam> getOptionalQueryParamList() {
        if (optionalQueryParamList == null) {
            optionalQueryParamList = Collections.emptyList();
        }

        return optionalQueryParamList;
    }

    /**
     * Request Builder
     * The only way to create an object of the UaiRequest type
     */
    public static class UaiRequestBuilder {
        private String name;
        private String path;
        private String body;
        private String method;
        private String description;
        private String requiredContentType;

        private Long holdTheRequestInMilli;

        private Boolean isBodyRequired;

        private List<UaiHeader> requiredHeaderList;
        private List<UaiHeader> optionalHeaderList;

        private List<UaiQueryParam> requiredQueryParamList;
        private List<UaiQueryParam> optionalQueryParamList;

        private BodyValidationType bodyValidationType;

        public UaiRequestBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public UaiRequestBuilder body(final String body) {
            this.body = body;
            return this;
        }

        public UaiRequestBuilder path(final String path) {
            this.path = path;
            return this;
        }

        public UaiRequestBuilder method(final String method) {
            this.method = method;
            return this;
        }

        public UaiRequestBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public UaiRequestBuilder requiredContentType(final String requiredContentType) {
            this.requiredContentType = requiredContentType;
            return this;
        }

        public UaiRequestBuilder holdTheRequestInMilli(final Long holdTheRequestInMilli) {
            this.holdTheRequestInMilli = holdTheRequestInMilli;
            return this;
        }

        public UaiRequestBuilder isBodyRequired(final Boolean isBodyRequired) {
            this.isBodyRequired = isBodyRequired;
            return this;
        }

        public UaiRequestBuilder requiredHeaderList(final List<UaiHeader> requiredHeaderList) {
            this.requiredHeaderList = requiredHeaderList;
            return this;
        }

        public UaiRequestBuilder optionalHeaderList(final List<UaiHeader> optionalHeaderList) {
            this.optionalHeaderList = optionalHeaderList;
            return this;
        }

        public UaiRequestBuilder requiredQueryParamList(final List<UaiQueryParam> requiredQueryParamList) {
            this.requiredQueryParamList = requiredQueryParamList;
            return this;
        }

        public UaiRequestBuilder optionalQueryParamList(final List<UaiQueryParam> optionalQueryParamList) {
            this.optionalQueryParamList = optionalQueryParamList;
            return this;
        }

        public UaiRequestBuilder bodyValidationType(final BodyValidationType bodyValidationType) {
            this.bodyValidationType = bodyValidationType;
            return this;
        }

        public UaiRequest build() {
            if (bodyValidationType == null && isBodyRequired != null && isBodyRequired) {
                bodyValidationType = BodyValidationType.VALIDATE_IF_PRESENT_ONLY;
            }

            return new UaiRequest(this);
        }
    }
}