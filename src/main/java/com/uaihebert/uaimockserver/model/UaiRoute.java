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

import com.uaihebert.uaimockserver.service.UaiRouteService;

import java.util.UUID;

/**
 * Class that will contain a Request Route.
 * A route is composed of a request and response
 */
public class UaiRoute {
    private String id;
    private String project;
    private UaiFile uaiFile;
    private UaiRequest request;
    private UaiResponse response;

    private boolean temporary;

    private Integer temporaryRepliesLeft;
    private Integer temporaryRepliesTotal;

    public UaiRoute() {

    }

    public UaiRoute(final UaiFile uaiFile, final UaiRequest request, final UaiResponse response, final String project) {
        createId();
        this.uaiFile = uaiFile;
        this.request = request;
        this.response = response;
        this.project = project;
    }

    public UaiRoute(final String id, final UaiFile uaiFile, final UaiRequest request, final UaiResponse response, final String project) {
        this.id = id;
        this.uaiFile = uaiFile;
        this.request = request;
        this.response = response;
        this.project = project;
    }

    public void createId() {
        id = UUID.randomUUID().toString();
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

    public UaiFile getUaiFile() {
        return uaiFile;
    }

    public void setUaiFile(final UaiFile uaiFile) {
        this.uaiFile = uaiFile;
    }

    public UaiRequest getRequest() {
        return request;
    }

    public UaiResponse getResponse() {
        return response;
    }

    public void setRequest(final UaiRequest request) {
        this.request = request;
    }

    public void setResponse(final UaiResponse response) {
        this.response = response;
    }

    public String getProject() {
        return project;
    }

    public void setProject(final String project) {
        this.project = project;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public Integer getTemporaryRepliesTotal() {
        return temporaryRepliesTotal;
    }

    public void setTemporaryRepliesTotal(Integer temporaryRepliesTotal) {
        this.temporaryRepliesLeft = temporaryRepliesTotal;
        this.temporaryRepliesTotal = temporaryRepliesTotal;
    }

    public void finishRequest() {
        if (!temporary) {
            return;
        }

        temporaryRepliesLeft--;

        if (temporaryRepliesLeft < 1) {
            UaiRouteService.deleteRoute(getId());
        }
    }
}