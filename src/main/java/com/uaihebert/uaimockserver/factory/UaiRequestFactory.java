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

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.constants.RequestConstants;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.util.ConfigKeyUtil;

import java.util.List;

/**
 * This factory will create an instance of the UaiRequest.java
 */
public final class UaiRequestFactory {
    private UaiRequestFactory() {
    }

    public static UaiRequest create(final Config config) {
        final Boolean bodyRequired = ConfigKeyUtil.getBooleanSilently(RequestConstants.IS_BODY_REQUIRED.path, config);

        final long holdRequestInMilli = ConfigKeyUtil.getLongSilently(RequestConstants.HOLD_THE_REQUEST_IN_MILLI.path, config);

        final String name = ConfigKeyUtil.getStringSilently(RequestConstants.NAME.path, config);
        final String path = ConfigKeyUtil.getStringSilently(RequestConstants.PATH.path, config);
        final String method = ConfigKeyUtil.getStringSilently(RequestConstants.METHOD.path, config);
        final String description = ConfigKeyUtil.getStringSilently(RequestConstants.DESCRIPTION.path, config);
        final String contentType = ConfigKeyUtil.getStringSilently(RequestConstants.CONTENT_TYPE.path, config);

        final List<UaiHeader> requiredHeaderList = UaiHeaderFactory.create(config, RequestConstants.REQUIRED_HEADER_LIST.path);
        final List<UaiQueryParam> requiredQueryParamList = UaiQueryParamFactory.create(config);

        return new UaiRequest(name, path, method, description, contentType, holdRequestInMilli, bodyRequired, requiredHeaderList, requiredQueryParamList);
    }
}