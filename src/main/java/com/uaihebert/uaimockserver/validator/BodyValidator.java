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

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiRequest;
import io.undertow.server.HttpServerExchange;

/**
 * Will validate all the request body if needed
 */
public final class BodyValidator implements RequestDataValidator {
    private static final String NO_BODY_MESSAGE = "No Request Body was detected in the request";
    private static final String RECEIVED_BODY_MESSAGE = "We received the following body: [%s]";

    @Override
    public void validate(final UaiRequest uaiRequest, final HttpServerExchange exchange, final RequestValidatorFacade.RequestAnalysisResult result) {
        final String body = result.currentBody;

        final boolean requestHasNoBody = exchange == null || exchange.getRequestContentLength() < 1;

        if (requestHasNoBody) {
            Log.info(NO_BODY_MESSAGE);
        } else {
            Log.infoFormatted(RECEIVED_BODY_MESSAGE, body);
        }

        if (uaiRequest.isBodyRequired == null || !uaiRequest.isBodyRequired) {
            return;
        }

        uaiRequest.getBodyValidationType().validate(body, uaiRequest, result);
    }
}