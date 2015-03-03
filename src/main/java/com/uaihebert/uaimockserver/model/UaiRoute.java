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

import java.util.UUID;

/**
 * Class that will contain a Request Route.
 * A route is composed of a request and response
 */
public class UaiRoute {
    private String id;
    private UaiFile uaiFile;
    private UaiRequest request;
    private UaiResponse response;

    public UaiRoute() {

    }

    public UaiRoute(final UaiFile uaiFile, final UaiRequest request, final UaiResponse response) {
        createId();
        this.uaiFile = uaiFile;
        this.request = request;
        this.response = response;
    }

    public void createId() {
        id = UUID.randomUUID().toString();
    }

    public UaiRoute(final String id, final UaiFile uaiFile, final UaiRequest request, final UaiResponse response) {
        this.id = id;
        this.uaiFile = uaiFile;
        this.request = request;
        this.response = response;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof UaiRoute) {
            final UaiRoute uaiRoute = (UaiRoute) o;
            return uaiRoute.id.equals(id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public UaiFile getUaiFile() {
        return uaiFile;
    }

    public void setUaiFile(final UaiFile uaiFile) {
        this.uaiFile = uaiFile;
    }

    public UaiRequest getRequest() {
        return request;
    }

    public void setRequest(final UaiRequest request) {
        this.request = request;
    }

    public UaiResponse getResponse() {
        return response;
    }

    public void setResponse(final UaiResponse response) {
        this.response = response;
    }
}