package com.uaihebert.uaimockserver.facade;

import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.validator.BodyValidator;
import com.uaihebert.uaimockserver.validator.ContentTypeValidator;
import com.uaihebert.uaimockserver.validator.OptionalHeaderValidator;
import com.uaihebert.uaimockserver.validator.OptionalQueryParamValidator;
import com.uaihebert.uaimockserver.validator.RequestDataValidator;
import com.uaihebert.uaimockserver.validator.RequiredHeaderValidator;
import com.uaihebert.uaimockserver.validator.RequiredQueryParamValidator;
import io.undertow.server.HttpServerExchange;

import java.util.ArrayList;
import java.util.List;

public final class RequestValidatorFacade {

    private static final List<RequestDataValidator> REQUEST_DATA_VALIDATOR_LIST = new ArrayList<RequestDataValidator>();

    static {
        reload();
    }

    private static void reload() {
        REQUEST_DATA_VALIDATOR_LIST.clear();
        REQUEST_DATA_VALIDATOR_LIST.add(new BodyValidator());
        REQUEST_DATA_VALIDATOR_LIST.add(new ContentTypeValidator());
        REQUEST_DATA_VALIDATOR_LIST.add(new OptionalHeaderValidator());
        REQUEST_DATA_VALIDATOR_LIST.add(new RequiredHeaderValidator());
        REQUEST_DATA_VALIDATOR_LIST.add(new OptionalQueryParamValidator());
        REQUEST_DATA_VALIDATOR_LIST.add(new RequiredQueryParamValidator());
    }

    private RequestValidatorFacade() {
    }

    public static RequestAnalysisResult isValidRequest(final UaiRequest uaiRequest, final HttpServerExchange exchange, final String requestBody) {
        reload();

        final RequestAnalysisResult requestAnalysisResult = new RequestAnalysisResult(requestBody);

        for (RequestDataValidator requestDataValidator : REQUEST_DATA_VALIDATOR_LIST) {
            requestDataValidator.validate(uaiRequest, exchange, requestAnalysisResult);
        }

        return requestAnalysisResult;
    }

    public static class RequestAnalysisResult {
        public final String currentBody;

        private boolean valid = true;
        private boolean abortTheRequest;

        public RequestAnalysisResult(final String currentBody) {
            this.currentBody = currentBody;
        }

        public boolean isValid() {
            return valid;
        }

        public boolean isAbortTheRequest() {
            return abortTheRequest;
        }

        public void abortTheRequest() {
            setInvalid();
            abortTheRequest = true;
        }

        public void setInvalid() {
            valid = false;
        }
    }
}