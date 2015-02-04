package com.uaihebert.uaimockserver.facade;

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.validator.BodyValidator;
import com.uaihebert.uaimockserver.validator.ContentTypeValidator;
import com.uaihebert.uaimockserver.validator.HeaderValidator;
import com.uaihebert.uaimockserver.validator.UaiQueryParamValidator;
import io.undertow.server.HttpServerExchange;

public final class RequestValidatorFacade {
    private RequestValidatorFacade() {
    }

    public static void validateRequest(final UaiRequest uaiRequest, final HttpServerExchange exchange, final UaiMockServerConfig uaiMockServerConfig) {
        ContentTypeValidator.validate(uaiRequest, exchange, uaiMockServerConfig);

        HeaderValidator.validate(uaiRequest, exchange, uaiMockServerConfig);

        UaiQueryParamValidator.validate(uaiRequest, exchange, uaiMockServerConfig);

        BodyValidator.validate(uaiRequest, exchange, uaiMockServerConfig);
    }
}