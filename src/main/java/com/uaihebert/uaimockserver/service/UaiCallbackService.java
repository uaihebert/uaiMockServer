package com.uaihebert.uaimockserver.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiCallback;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import org.apache.commons.lang3.StringUtils;

public final class UaiCallbackService {
    private UaiCallbackService() {
    }

    public static void executeCallBack(final UaiCallback callback) {
        if (callback == null) {
            return;
        }

        try {
            final HttpResponse<JsonNode> result = callback(callback);

            final String logText = new StringBuilder()
                .append("Callback executed. \n")
                .append("We called the url " + callback.getCompleteUrlToCall() +  "\n")
                .append("The returned Http Status Code was: " + result.getStatus() + ". \n")
                .append("The returned Response Body is: " + result.getBody() + ". \n")
                .toString();

            Log.infoFormatted(logText);
        } catch (UnirestException ex) {
            Log.warnFormatted(ex);
        }
    }

    private static HttpResponse<JsonNode> callback(final UaiCallback callback) throws UnirestException {
        final GetRequest request = Unirest.get(callback.getCompleteUrlToCall());

        for (final UaiQueryParam queryParam : callback.getQueryParamList()) {
            request.queryString(queryParam.getName(), queryParam.getValueList());
        }

        for (final UaiHeader header : callback.getHeaderList()) {
            final String value = StringUtils.join(header.getValueList(), ",");
            request.header(header.getName(), value);
        }

        return request.asJson();
    }
}
