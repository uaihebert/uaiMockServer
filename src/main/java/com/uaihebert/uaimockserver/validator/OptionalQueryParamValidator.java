package com.uaihebert.uaimockserver.validator;

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade.RequestAnalysisResult;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.model.UaiRequest;
import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;

public class OptionalQueryParamValidator implements RequestDataValidator {

    @SuppressWarnings("LineLength")
    private static final String WILD_CARD_USED = "The header [%s] is using the wildcard. Its content will not be checked.";

    @SuppressWarnings("LineLength")
    private static final String OPTIONAL_HEADER_NOT_FOUND = "The optional header [%s] was not found in the request. This route will not be used.";

    @SuppressWarnings("LineLength")
    private static final String HEADER_VALUE_NOT_FOUND_MESSAGE = "%nThe required value %s was not found in the header [%s]";

    @Override
    public void validate(final UaiRequest uaiRequest,
                         final HttpServerExchange exchange,
                         final RequestAnalysisResult result) {
        for (UaiQueryParam uaiQueryParam : uaiRequest.getOptionalQueryParamList()) {
            final Map<String, Deque<String>> queryParameterMap = exchange.getQueryParameters();

            validatedQueryParam(uaiQueryParam, queryParameterMap, result);
        }
    }

    private void validatedQueryParam(final UaiQueryParam uaiQueryParam,
                                     final Map<String, Deque<String>> queryParameterMap,
                                     final RequestAnalysisResult result) {
        final Deque<String> valueDeque = queryParameterMap.get(uaiQueryParam.getName());

        if (valueDeque == null) {
            Log.infoFormatted(OPTIONAL_HEADER_NOT_FOUND, uaiQueryParam.getName());
            result.setInvalid();
            return;
        }

        if (uaiQueryParam.isUsingWildCard()) {
            Log.infoFormatted(WILD_CARD_USED, uaiQueryParam.getName());
            return;
        }

        if (!valueDeque.containsAll(uaiQueryParam.getValueList())) {
            Log.warnFormatted(HEADER_VALUE_NOT_FOUND_MESSAGE, uaiQueryParam.getValueList(), uaiQueryParam.getName());
            result.abortTheRequest();
        }
    }
}
