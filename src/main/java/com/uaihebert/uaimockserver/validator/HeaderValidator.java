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
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiRequest;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HeaderValues;

/**
 * Will validate all the request headers if needed
 */
public final class HeaderValidator implements RequestDataValidator {
    private static final String HEADER_VALUE_NOT_FOUND_MESSAGE = "The required value [%s] was not found in the header [%s]";

    @Override
    public boolean isInvalid(final UaiRequest uaiRequest, final HttpServerExchange exchange) {
        for (UaiHeader uaiHeader : uaiRequest.getRequiredHeaderList()) {
            final HeaderMap requestHeaderMap = exchange.getRequestHeaders();

            if (isInvalidHeader(uaiHeader, requestHeaderMap)) {
                return true;
            }
        }

        return false;
    }

    private boolean isInvalidHeader(final UaiHeader uaiHeader, final HeaderMap requestHeaderMap) {
        final HeaderValues headerValueList = requestHeaderMap.get(uaiHeader.getName());

        if (uaiHeader.isUsingWildCard()) {
            Log.infoFormatted("The header [%s] is using the wildcard. Its content will not be checked.", uaiHeader.getName());
            return false;
        }

        if (!headerValueList.containsAll(uaiHeader.getValueList())) {
            Log.warn(HEADER_VALUE_NOT_FOUND_MESSAGE, uaiHeader.getName(), uaiHeader.getValueList());
            return true;
        }

        return false;
    }
}