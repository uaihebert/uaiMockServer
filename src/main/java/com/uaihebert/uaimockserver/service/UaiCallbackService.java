package com.uaihebert.uaimockserver.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiCallback;

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
        return Unirest.get(callback.getCompleteUrlToCall()).asJson();
    }
}
