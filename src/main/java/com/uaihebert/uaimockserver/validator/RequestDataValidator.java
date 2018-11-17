package com.uaihebert.uaimockserver.validator;

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade.RequestAnalysisResult;
import com.uaihebert.uaimockserver.model.UaiRequest;
import io.undertow.server.HttpServerExchange;

public interface RequestDataValidator {
    void validate(UaiRequest uaiRequest, HttpServerExchange exchange, RequestAnalysisResult result);
}
