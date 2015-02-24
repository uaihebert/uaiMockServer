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
    public final String id;
    public final UaiFile uaiFile;
    public final UaiRequest uaiRequest;
    public final UaiResponse uaiResponse;

    public UaiRoute(final UaiFile uaiFile, final UaiRequest uaiRequest, final UaiResponse uaiResponse) {
        id = UUID.randomUUID().toString();
        this.uaiFile = uaiFile;
        this.uaiRequest = uaiRequest;
        this.uaiResponse = uaiResponse;
    }

    public UaiRoute(final String id, final UaiFile uaiFile, final UaiRequest uaiRequest, final UaiResponse uaiResponse) {
        this.id = id;
        this.uaiFile = uaiFile;
        this.uaiRequest = uaiRequest;
        this.uaiResponse = uaiResponse;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UaiRoute uaiRoute = (UaiRoute) o;

        return id.equals(uaiRoute.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}