package com.uaihebert.uaimockserver.facade;

import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.validator.BodyValidator;
import com.uaihebert.uaimockserver.validator.ContentTypeValidator;
import com.uaihebert.uaimockserver.validator.HeaderValidator;
import com.uaihebert.uaimockserver.validator.UaiQueryParamValidator;
import io.undertow.server.HttpServerExchange;

public final class RequestValidatorFacade {
    private RequestValidatorFacade() {
    }

    // todo create validator as interface and iterate over a list here
    public static boolean isValidRequest(final UaiRequest uaiRequest, final HttpServerExchange exchange) {
        if (ContentTypeValidator.isInvalid(uaiRequest, exchange)) {
            return false;
        }

        if (HeaderValidator.isInvalid(uaiRequest, exchange)) {
            return false;
        }

        if (UaiQueryParamValidator.isInvalid(uaiRequest, exchange)) {
            return false;
        }

        return !BodyValidator.isInvalid(uaiRequest, exchange);
    }
}