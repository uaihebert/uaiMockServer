package com.uaihebert.uaimockserver.facade;

import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.validator.BodyValidator;
import com.uaihebert.uaimockserver.validator.ContentTypeValidator;
import com.uaihebert.uaimockserver.validator.HeaderValidator;
import com.uaihebert.uaimockserver.validator.RequestDataValidator;
import com.uaihebert.uaimockserver.validator.UaiQueryParamValidator;
import io.undertow.server.HttpServerExchange;

import java.util.ArrayList;
import java.util.List;

public final class RequestValidatorFacade {

    private static final List<RequestDataValidator> REQUEST_DATA_VALIDATOR_LIST = new ArrayList<RequestDataValidator>();

    static {
        REQUEST_DATA_VALIDATOR_LIST.add(new BodyValidator());
        REQUEST_DATA_VALIDATOR_LIST.add(new HeaderValidator());
        REQUEST_DATA_VALIDATOR_LIST.add(new ContentTypeValidator());
        REQUEST_DATA_VALIDATOR_LIST.add(new UaiQueryParamValidator());
    }

    private RequestValidatorFacade() {
    }

    public static boolean isValidRequest(final UaiRequest uaiRequest, final HttpServerExchange exchange) {
        for (RequestDataValidator requestDataValidator : REQUEST_DATA_VALIDATOR_LIST) {
            if (requestDataValidator.isInvalid(uaiRequest, exchange)) {
                return false;
            }
        }

        return true;
    }
}