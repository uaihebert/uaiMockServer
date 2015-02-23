package com.uaihebert.uaimockserver.validator;

import com.uaihebert.uaimockserver.model.UaiRequest;
import io.undertow.server.HttpServerExchange;

public interface RequestDataValidator {
    public boolean isInvalid(final UaiRequest uaiRequest, final HttpServerExchange exchange);
}
