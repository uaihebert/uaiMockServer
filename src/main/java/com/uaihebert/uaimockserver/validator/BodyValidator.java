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

import com.uaihebert.uaimockserver.configuration.ProjectConfiguration;
import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.BodyValidationType;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.util.RequestBodyUtil;
import io.undertow.server.HttpServerExchange;

import java.util.Scanner;

/**
 * Will validate all the request body if needed
 */
public final class BodyValidator implements RequestDataValidator {
    private static final String NO_BODY_MESSAGE = "No Request Body was detected in the request";
    private static final String RECEIVED_BODY_MESSAGE = "We received the following body: [%s]";
    private static final String WRONG_RAW_TEXT_BODY = "We received a body with the following text in the body [%s], but the required body is [%s]";
    private static final String BODY_VALIDATOR_ERROR_MESSAGE = "%nThe Route [%s - %s] was defined with the body as mandatory. Send a body in your request or set the bodyRequired to false. %n";

    @Override
    public void validate(final UaiRequest uaiRequest, final HttpServerExchange exchange, final RequestValidatorFacade.RequestAnalysisResult result) {
        final String body = extractBody(exchange);

        final boolean requestHasNoBody = exchange == null || exchange.getRequestContentLength() < 1;

        if (requestHasNoBody) {
            Log.info(NO_BODY_MESSAGE);
        } else {
            final String bodyAsString = RequestBodyUtil.convertToString(exchange);
            Log.infoFormatted(RECEIVED_BODY_MESSAGE, bodyAsString);
        }

        if (uaiRequest.isBodyRequired == null || !uaiRequest.isBodyRequired) {
            return;
        }

        if (requestHasNoBody) {
            Log.warn(BODY_VALIDATOR_ERROR_MESSAGE, uaiRequest.method, uaiRequest.path);
            result.abortTheRequest();

            if (BodyValidationType.VALIDATE_IF_PRESENT_ONLY.equals(uaiRequest.bodyValidationType)) {
                return;
            }
        }

        if (BodyValidationType.RAW_TEXT.equals(uaiRequest.bodyValidationType)) {
            if (!uaiRequest.body.equals(body)) {
                Log.warn(WRONG_RAW_TEXT_BODY, body, uaiRequest.body);
                result.abortTheRequest();
                return;
            }
        }
    }

    private String extractBody(final HttpServerExchange exchange) {
        if (exchange == null) {
            return null;
        }

        exchange.startBlocking();

        final Scanner scanner = new Scanner(exchange.getInputStream(), ProjectConfiguration.ENCODING.value).useDelimiter("\\A");

        if (!scanner.hasNext()) {
            return null;
        }

        return scanner.next();
    }
}