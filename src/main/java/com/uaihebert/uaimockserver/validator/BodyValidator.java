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

import com.uaihebert.uaimockserver.log.Log;
import com.uaihebert.uaimockserver.model.UaiRequest;
import io.undertow.server.HttpServerExchange;

/**
 * Will validate all the request body if needed
 */
public final class BodyValidator implements RequestDataValidator {
    private static final String BODY_VALIDATOR_ERROR_MESSAGE = "The Route [%s - %s] was defined with the body as mandatory. Send a body in your request or set the bodyRequired to false. %n";

    @Override
    public boolean isInvalid(final UaiRequest uaiRequest, final HttpServerExchange exchange) {
        if (uaiRequest.isBodyRequired() && exchange.getRequestContentLength() < 1) {
            Log.warn(BODY_VALIDATOR_ERROR_MESSAGE, uaiRequest.getMethod(), uaiRequest.getPath());
            return true;
        }

        return false;
    }
}