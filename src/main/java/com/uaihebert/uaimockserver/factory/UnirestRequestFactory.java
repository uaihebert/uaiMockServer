package com.uaihebert.uaimockserver.factory;

import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.uaihebert.uaimockserver.constants.UaiHttpMethod;
import com.uaihebert.uaimockserver.model.UaiCallback;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public final class UnirestRequestFactory {
    private static final List<UaiHttpMethod> NO_BODY_ALLOWED = Arrays.asList(UaiHttpMethod.GET, UaiHttpMethod.HEAD);

    private UnirestRequestFactory() {
    }

    public static HttpRequest create(final UaiCallback callback) {
        if (NO_BODY_ALLOWED.contains(callback.getHttpMethod())) {
            return Unirest.get(callback.getCompleteUrlToCall());
        }

        final HttpMethod httpMethod = HttpMethod.valueOf(callback.getHttpMethod().name());
        final HttpRequestWithBody request = new HttpRequestWithBody(httpMethod, callback.getCompleteUrlToCall());

        if (StringUtils.isNotBlank(callback.getBodyToSend())) {
            request.body(callback.getBodyToSend());
        }

        return request;
    }
}
