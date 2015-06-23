package com.uaihebert.uaimockserver.build;

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.model.*;
import com.uaihebert.uaimockserver.service.UaiRouteService;

import java.util.ArrayList;
import java.util.List;

public final class UaiRouteBuilder {
    // response data
    private String responseBody;
    private int statusCode;
    private String bodyPath;
    private String contentType;
    private List<UaiHeader> headerList;

    // request data
    private List<UaiHeader> requiredHeaderList;
    private List<UaiHeader> optionalHeaderList;
    private List<UaiQueryParam> requiredQueryParamList;
    private List<UaiQueryParam> optionalQueryParamList;
    private UaiRequest.UaiRequestBuilder requestBuilder;

    private UaiRouteBuilder() {
        requestBuilder = new UaiRequest.UaiRequestBuilder();
    }

    public static UaiRouteBuilder aBuilder() {
        return new UaiRouteBuilder();
    }

    public UaiRouteBuilder withPath(final String path) {
        requestBuilder.path(path);
        return this;
    }

    public UaiRouteBuilder withMethod(final String method) {
        requestBuilder.method(method);
        return this;
    }

    public UaiRouteBuilder withRequiredContentType(final String requiredContentType) {
        requestBuilder.requiredContentType(requiredContentType);
        return this;
    }

    public UaiRouteBuilder withHoldTheRequestInMilli(final Long oldTheRequestInMilli) {
        requestBuilder.holdTheRequestInMilli(oldTheRequestInMilli);
        return this;
    }

    public UaiRouteBuilder withBodyRequired(final String body, final BodyValidationType bodyValidationType) {
        requestBuilder.body(body);
        requestBuilder.isBodyRequired(true);
        requestBuilder.bodyValidationType(bodyValidationType);
        return this;
    }

    public UaiRouteBuilder withRequiredHeader(final String key, final List<String> valueList) {
        if (requiredHeaderList == null) {
            requiredHeaderList = new ArrayList<UaiHeader>();
        }

        requiredHeaderList.add(new UaiHeader(key, valueList));
        return this;
    }

    public UaiRouteBuilder withOptionalHeader(final String key, final List<String> valueList) {
        if (optionalHeaderList == null) {
            optionalHeaderList = new ArrayList<UaiHeader>();
        }

        optionalHeaderList.add(new UaiHeader(key, valueList));
        return this;
    }

    public UaiRouteBuilder withRequiredQueryParam(final String key, final List<String> valueList) {
        if (requiredQueryParamList == null) {
            requiredQueryParamList = new ArrayList<UaiQueryParam>();
        }

        requiredQueryParamList.add(new UaiQueryParam(key, valueList));
        return this;
    }

    public UaiRouteBuilder withOptionalQueryParam(final String key, final List<String> valueList) {
        if (optionalQueryParamList == null) {
            optionalQueryParamList = new ArrayList<UaiQueryParam>();
        }

        optionalQueryParamList.add(new UaiQueryParam(key, valueList));
        return this;
    }

    public UaiRouteBuilder withResponseCode(int responseCode) {
        statusCode = responseCode;
        return this;
    }

    public UaiRouteBuilder withResponseBody(final String body) {
        this.responseBody = body;
        return this;
    }

    public UaiRouteBuilder withResponseBodyInFile(final String bodyPath) {
        this.bodyPath = bodyPath;
        return this;
    }

    public UaiRouteBuilder withResponseContentType(final String contentType) {
        this.contentType = contentType;
        return this;
    }

    public UaiRouteBuilder addResponseHeader(final String key, final List<String> valueList) {
        if (headerList == null) {
            headerList = new ArrayList<UaiHeader>();
        }

        headerList.add(new UaiHeader(key, valueList));
        return this;
    }

    public void build() {
        buildAsTemporary(0);
    }

    public void buildAsTemporary(final int temporaryRepliesTotal) {
        final UaiFile uaiFile = UaiMockServerContext.getInstance().uaiMockServerConfig.getUaiFile();

        final UaiRequest request = requestBuilder.build();
        final UaiResponse response = new UaiResponse(statusCode, responseBody, contentType, headerList, bodyPath);

        final UaiRoute uaiRoute = new UaiRoute(uaiFile, request, response, null);

        if (temporaryRepliesTotal > 0) {
            uaiRoute.setTemporary(true);
            uaiRoute.setTemporaryRepliesTotal(temporaryRepliesTotal);
        }

        UaiRouteService.createRoute(uaiRoute);
    }
}