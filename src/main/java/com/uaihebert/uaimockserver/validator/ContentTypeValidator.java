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
package com.uaihebert.uaimockserver.validator;

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.util.ExceptionUtil;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;
import io.undertow.util.Headers;
import org.apache.commons.lang3.StringUtils;

/**
 * Will validate the request contentType if needed
 */
public final class ContentTypeValidator {
    private static final String HEADER_VALUE_NOT_FOUND_MESSAGE = "The required contentType [%s] was not found in the header request. Make sure that you have send it";

    private ContentTypeValidator() {
    }

    public static void validate(final UaiRequest uaiRequest, final HttpServerExchange exchange, final UaiMockServerConfig uaiMockServerConfig) {
        final String requiredContentType = uaiRequest.requiredContentType;

        if (StringUtils.isEmpty(requiredContentType)) {
            return;
        }

        final HeaderValues headerValues = exchange.getRequestHeaders().get(Headers.CONTENT_TYPE);

        if (headerValues == null) {
            final String errorText = String.format(HEADER_VALUE_NOT_FOUND_MESSAGE, requiredContentType);
            ExceptionUtil.logBeforeThrowing(new IllegalArgumentException(errorText), uaiMockServerConfig.basicConfiguration.log);
        }
    }
}