package com.uaihebert.uaimockserver.validator;

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiRequest;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HeaderValues;

public class OptionalHeaderValidator implements RequestDataValidator{
    private static final String WILD_CARD_USED = "The header [%s] is using the wildcard. Its content will not be checked.";
    private static final String OPTIONAL_HEADER_NOT_FOUND = "The optional header [%s] was not found in the request. This route will not be used.";
    private static final String HEADER_VALUE_NOT_FOUND_MESSAGE = "%nThe required value %s was not found in the header [%s]";

    @Override
    public void validate(final UaiRequest uaiRequest, final HttpServerExchange exchange, final RequestValidatorFacade.RequestAnalysisResult result) {
        for (UaiHeader uaiHeader : uaiRequest.getOptionalHeaderList()) {
            final HeaderMap requestHeaderMap = exchange.getRequestHeaders();

            validateHeader(uaiHeader, requestHeaderMap, result);
        }
    }

    private void validateHeader(final UaiHeader uaiHeader, final HeaderMap requestHeaderMap, final RequestValidatorFacade.RequestAnalysisResult result) {
        final HeaderValues headerValueList = requestHeaderMap.get(uaiHeader.getName());

        if (headerValueList == null) {
            Log.infoFormatted(OPTIONAL_HEADER_NOT_FOUND, uaiHeader.getName());
            result.setInvalid();
            return;
        }

        if (uaiHeader.isUsingWildCard()) {
            Log.infoFormatted(WILD_CARD_USED, uaiHeader.getName());
            return;
        }

        if (!headerValueList.containsAll(uaiHeader.getValueList())) {
            Log.warn(HEADER_VALUE_NOT_FOUND_MESSAGE, uaiHeader.getValueList(), uaiHeader.getName());
            result.abortTheRequest();
        }
    }
}