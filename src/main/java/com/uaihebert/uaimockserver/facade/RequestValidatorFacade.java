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

    public static void validateRequest(final UaiRequest uaiRequest, final HttpServerExchange exchange) {
        ContentTypeValidator.validate(uaiRequest, exchange);

        HeaderValidator.validate(uaiRequest, exchange);

        UaiQueryParamValidator.validate(uaiRequest, exchange);

        BodyValidator.validate(uaiRequest, exchange);
    }
}