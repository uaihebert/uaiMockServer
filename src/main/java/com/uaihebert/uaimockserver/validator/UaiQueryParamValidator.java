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

import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.model.UaiRequest;
import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;

/**
 * Will validate all the request query params if needed
 */
public final class UaiQueryParamValidator implements RequestDataValidator {
    private static final String QUERY_PARAM_VALUE_NOT_FOUND_MESSAGE = "The required queryParamList [%s] has not the required values: [%s]";

    @Override
    public boolean isInvalid(final UaiRequest uaiRequest, final HttpServerExchange exchange) {
        for (UaiQueryParam uaiQueryParam : uaiRequest.getRequiredQueryParamList()) {
            final Map<String, Deque<String>> queryParameterMap = exchange.getQueryParameters();

            if (isInvalidQueryParam(uaiQueryParam, queryParameterMap)) {
                return true;
            }
        }

        return false;
    }

    private boolean isInvalidQueryParam(final UaiQueryParam uaiQueryParam, final Map<String, Deque<String>> queryParameterMap) {
        final Deque<String> valueDeque = queryParameterMap.get(uaiQueryParam.getName());

        if (uaiQueryParam.isUsingWildCard()) {
            Log.infoFormatted("The header [%s] is using the wildcard. Its content will not be checked.", uaiQueryParam.getName());
            return false;
        }

        if (!valueDeque.containsAll(uaiQueryParam.getValueList())) {
            Log.warn(QUERY_PARAM_VALUE_NOT_FOUND_MESSAGE, uaiQueryParam.getName(), uaiQueryParam.getValueList());
            return true;
        }

        return false;
    }
}