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

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade.RequestAnalysisResult;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiRequest;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HeaderValues;

/**
 * Will validate all the request headers if needed.
 */
public final class RequiredHeaderValidator implements RequestDataValidator {

    @SuppressWarnings("LineLength")
    private static final String WILD_CARD_USED = "The header [%s] is using the wildcard. Its content will not be checked.";

    @SuppressWarnings("LineLength")
    private static final String REQUIRED_HEADER_NOT_FOUND = "The required header [%s] was not found in the request";

    @SuppressWarnings("LineLength")
    private static final String HEADER_VALUE_NOT_FOUND_MESSAGE = "%nThe required value %s was not found in the header [%s]";

    @Override
    public void validate(final UaiRequest uaiRequest,
                         final HttpServerExchange exchange,
                         final RequestAnalysisResult result) {
        for (UaiHeader uaiHeader : uaiRequest.getRequiredHeaderList()) {
            final HeaderMap requestHeaderMap = exchange.getRequestHeaders();

            validateHeader(uaiHeader, requestHeaderMap, result);
        }
    }

    private void validateHeader(final UaiHeader uaiHeader,
                                final HeaderMap requestHeaderMap,
                                final RequestAnalysisResult result) {
        final HeaderValues headerValueList = requestHeaderMap.get(uaiHeader.getName());

        if (headerValueList == null) {
            Log.warnFormatted(REQUIRED_HEADER_NOT_FOUND, uaiHeader.getName());
            result.setInvalid();
            return;
        }

        if (uaiHeader.isUsingWildCard()) {
            Log.infoFormatted(WILD_CARD_USED, uaiHeader.getName());
            return;
        }

        if (!headerValueList.containsAll(uaiHeader.getValueList())) {
            Log.warnFormatted(HEADER_VALUE_NOT_FOUND_MESSAGE, uaiHeader.getValueList(), uaiHeader.getName());
            result.setInvalid();
        }
    }
}
