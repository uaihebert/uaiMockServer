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

/**
 * Will validate all the request body if needed
 */
public final class BodyValidator {
    private static final String BODY_VALIDATOR_ERROR_MESSAGE = "The Route [%s - %s] was defined with the body as mandatory. Send a body in your request or set the bodyRequired to false. \n";

    private BodyValidator() {
    }

    public static void validate(final UaiRequest uaiRequest, final HttpServerExchange exchange, final UaiMockServerConfig uaiMockServerConfig) {
        if (uaiRequest.isBodyRequired && exchange.getRequestContentLength() < 1) {
            final String errorText = String.format(BODY_VALIDATOR_ERROR_MESSAGE, uaiRequest.method, uaiRequest.path);
            ExceptionUtil.logBeforeThrowing(new IllegalArgumentException(errorText));
        }
    }
}