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
package com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.model.UaiFile;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.model.UaiResponse;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.StringUtils;

/**
 * This factory will create an instance of the UaiRoute.java
 */
public final class UaiRouteFactory {
    public static final String CLONE_PREFIX = "[CLONED] ";
    public static final String CLONE_SUFFIX = "_CLONED";

    private UaiRouteFactory() {
    }

    public static UaiRoute create(final UaiRouteDTO uaiRouteDTO) {
        final UaiRequest uaiRequest = UaiRequestFactory.create(uaiRouteDTO.getRequest());

        final UaiResponse uaiResponse = UaiResponseFactory.create(uaiRouteDTO.getResponse());

        final UaiFile uaiFile = new UaiFile(uaiRouteDTO.getUaiFile().getName(), uaiRouteDTO.getUaiFile().getFullPath());

        if (StringUtils.isBlank(uaiRouteDTO.getId())) {
            final UaiRoute uaiRoute = new UaiRoute(uaiFile, uaiRequest, uaiResponse, uaiRouteDTO.getProject());

            setTemporaryData(uaiRouteDTO, uaiRoute);

            return uaiRoute;
        }

        final UaiRoute uaiRoute = new UaiRoute(uaiRouteDTO.getId(), uaiFile, uaiRequest, uaiResponse, uaiRouteDTO.getProject());

        setTemporaryData(uaiRouteDTO, uaiRoute);

        return uaiRoute;
    }

    private static void setTemporaryData(UaiRouteDTO uaiRouteDTO, UaiRoute uaiRoute) {
        uaiRoute.setTemporary(uaiRouteDTO.isTemporary());
        uaiRoute.setTemporaryRepliesTotal(uaiRouteDTO.getTemporaryRepliesTotal());
    }

    public static void setDTOValueToEntity(final UaiRoute uaiRoute, final UaiRouteDTO uaiRouteDTO) {
        final UaiRequest uaiRequest = UaiRequestFactory.create(uaiRouteDTO.getRequest());

        final UaiResponse uaiResponse = UaiResponseFactory.create(uaiRouteDTO.getResponse());

        final UaiFile uaiFile = new UaiFile(uaiRouteDTO.getUaiFile().getName(), uaiRouteDTO.getUaiFile().getFullPath());

        uaiRoute.setRequest(uaiRequest);
        uaiRoute.setResponse(uaiResponse);
        uaiRoute.setUaiFile(uaiFile);
        uaiRoute.setProject(uaiRouteDTO.getProject());
    }

    public static UaiRoute clone(final UaiRoute uaiRoute) {
        final UaiRequest request = uaiRoute.getRequest();

        final String newName = CLONE_PREFIX + request.name;
        final String newPath = request.path + CLONE_SUFFIX;

        final UaiRequest clonedRequest = new UaiRequest.UaiRequestBuilder()
                .isBodyRequired(request.isBodyRequired)
                .holdTheRequestInMilli(request.holdTheRequestInMilli)
                .name(newName)
                .path(newPath)
                .method(request.method)
                .description(request.description)
                .body(request.body)
                .bodyValidationType(request.getBodyValidationType())
                .requiredContentType(request.requiredContentType)
                .requiredHeaderList(request.getRequiredHeaderList())
                .optionalHeaderList(request.getOptionalHeaderList())
                .requiredQueryParamList(request.getRequiredQueryParamList())
                .optionalQueryParamList(request.getOptionalQueryParamList())
                .build();

        return new UaiRoute(uaiRoute.getUaiFile(), clonedRequest, uaiRoute.getResponse(), uaiRoute.getProject());
    }
}