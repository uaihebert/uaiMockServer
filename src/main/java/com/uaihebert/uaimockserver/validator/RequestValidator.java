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
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.ExceptionUtil;
import io.undertow.server.HttpServerExchange;

/**
 * Will validate all data in the request
 */
public final class RequestValidator {
    private static final String URI_NOT_FOUND_MESSAGE = "We could not find the requested URI [%s] with the method [%s]. %n " +
            "Check the config file and try to find the mapping. A \\ in the end of the URI will affect the result. %n " +
            "Also check if all the required query param and/header were sent. %n";

    private RequestValidator() {
    }

    public static void validateRequest(final UaiRoute uaiRoute, final HttpServerExchange exchange) {
        if (noRouteFound(uaiRoute)) {
            final String errorText = String.format(URI_NOT_FOUND_MESSAGE, exchange.getRequestURI(), exchange.getRequestMethod());
            ExceptionUtil.logBeforeThrowing(new IllegalArgumentException(errorText));
        }

        RequestValidatorFacade.validateRequest(uaiRoute.uaiRequest, exchange);
    }

    private static boolean noRouteFound(final UaiRoute uaiRoute) {
        return uaiRoute == null;
    }
}