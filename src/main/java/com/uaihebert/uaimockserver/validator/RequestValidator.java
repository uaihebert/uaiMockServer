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
import com.uaihebert.uaimockserver.log.Log;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.ExceptionUtil;
import io.undertow.server.HttpServerExchange;

import java.util.Set;

/**
 * Will validate all data in the request
 */
public final class RequestValidator {
    private static final String URI_NOT_FOUND_MESSAGE = "We could not find the requested URI [%s] with the method [%s]. %n " +
            "Check the config file and try to find the mapping. A \\ in the end of the URI will affect the result. %n " +
            "Also check if all the required query param and/header were sent. %n";

    private static final String WRONG_HEADER_QUERY_PARAM_VALUE = "Check the QueryParam/Headers sent.  %n " +
            "We found the same headers but the values did not match";

    private RequestValidator() {
    }

    public static UaiRoute validateRequest(final Set<UaiRoute> uaiRouteList, final HttpServerExchange exchange) {
        if (noRouteFound(uaiRouteList)) {
            final String errorText = String.format(URI_NOT_FOUND_MESSAGE, exchange.getRequestURI(), exchange.getRequestMethod());
            ExceptionUtil.logBeforeThrowing(new IllegalArgumentException(errorText));
        }

        for (UaiRoute uaiRoute : uaiRouteList) {
            if (RequestValidatorFacade.isValidRequest(uaiRoute.uaiRequest, exchange)) {
                return uaiRoute;
            }
        }

        final String errorText = String.format(WRONG_HEADER_QUERY_PARAM_VALUE);
        Log.warn(errorText);

        throw new IllegalArgumentException(errorText);
    }

    private static boolean noRouteFound(final Set<UaiRoute> uaiRouteList) {
        return uaiRouteList.isEmpty();
    }
}