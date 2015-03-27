package com.uaihebert.uaimockserver.validator;

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.model.UaiRequest;
import io.undertow.server.HttpServerExchange;

public interface RequestDataValidator {
    void validate(final UaiRequest uaiRequest, final HttpServerExchange exchange, final RequestValidatorFacade.RequestAnalysisResult result);
}
