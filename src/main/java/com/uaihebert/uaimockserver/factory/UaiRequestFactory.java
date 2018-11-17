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

import com.uaihebert.uaimockserver.dto.model.UaiRequestDTO;
import com.uaihebert.uaimockserver.model.BodyValidationType;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.model.UaiRequest;

import java.util.List;

/**
 * This factory will create an instance of the UaiRequest.java.
 */
public final class UaiRequestFactory {
    private UaiRequestFactory() {
    }

    public static UaiRequest create(final UaiRequestDTO request) {
        final Boolean bodyRequired = request.isBodyRequired();

        final Long holdRequestInMilli = request.getHoldRequestInMilli();

        final String name = request.getName();
        final String path = request.getPath();
        final String body = request.getBody();
        final String method = request.getMethod();
        final String description = request.getDescription();
        final String contentType = request.getRequiredContentType();
        final BodyValidationType bodyValidationType = request.getBodyValidationType();

        final List<UaiHeader> requiredHeaderList = UaiHeaderFactory.create(request.getRequiredHeaderList());
        final List<UaiHeader> optionalHeaderList = UaiHeaderFactory.create(request.getOptionalHeaderList());
        final List<UaiQueryParam> requiredQueryParamList = UaiQueryParamFactory.create(
            request.getRequiredQueryParamList()
        );
        final List<UaiQueryParam> optionalQueryParamList = UaiQueryParamFactory.create(
            request.getOptionalQueryParamList()
        );

        return new UaiRequest.UaiRequestBuilder()
                .isBodyRequired(bodyRequired)
                .holdTheRequestInMilli(holdRequestInMilli)
                .name(name)
                .path(path)
                .body(body)
                .method(method)
                .description(description)
                .requiredContentType(contentType)
                .optionalHeaderList(optionalHeaderList)
                .requiredHeaderList(requiredHeaderList)
                .optionalQueryParamList(optionalQueryParamList)
                .requiredQueryParamList(requiredQueryParamList)
                .bodyValidationType(bodyValidationType)
                .build();
    }
}
