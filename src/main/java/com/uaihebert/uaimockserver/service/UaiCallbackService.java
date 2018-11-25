package com.uaihebert.uaimockserver.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.uaihebert.uaimockserver.factory.UnirestRequestFactory;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.log.gui.UaiWebSocketLogManager;
import com.uaihebert.uaimockserver.model.UaiCallback;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import org.apache.commons.lang3.StringUtils;

public final class UaiCallbackService {
    private UaiCallbackService() {
    }

    // Skipping it here because it makes no sense of throwing an exception up on a separated thread
    @SuppressWarnings("IllegalCatch")
    public static void executeCallback(final UaiCallback callback) {
        if (callback == null) {
            return;
        }

        Log.info("Scheduling callback to run in: " + callback.getDelayInMilli());

        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    // todo, add this callback call to the UI log
                    UaiWebSocketLogManager.start(callback);
                    runProcess(callback);
                } catch (Exception ex) {
                    Log.warnFormatted(ex);
                    UaiWebSocketLogManager.exceptionDetected("Error executing callback" + ex.getMessage());
                } finally {
                    UaiWebSocketLogManager.finishLog();
                }
            }
        };

        thread.start();
    }

    private static void runProcess(final UaiCallback callback) throws InterruptedException, UnirestException {
        Log.info("Holding callback execution for: " + callback.getDelayInMilli() + "ms");

        Thread.sleep(callback.getDelayInMilli());

        final HttpResponse<JsonNode> result = callback(callback);

        final String logText = new StringBuilder()
            .append("Callback executed. \n")
            .append("We called the url " + callback.getCompleteUrlToCall() + "\n")
            .append("The returned Http Status Code was: " + result.getStatus() + ". \n")
            .append("The returned Response Body is: " + result.getBody() + ". \n")
            .toString();

        Log.info(logText);
    }

    private static HttpResponse<JsonNode> callback(final UaiCallback callback) throws UnirestException {
        Log.info("Executing callback. The invoked URL will be: " + callback.getCompleteUrlToCall());

        final HttpRequest request = UnirestRequestFactory.create(callback);

        setHeader(callback, request);
        setQueryParam(callback, request);

        return request.asJson();
    }

    private static void setHeader(final UaiCallback callback, final HttpRequest request) {
        for (final UaiHeader header : callback.getHeaderList()) {
            final String value = StringUtils.join(header.getValueList(), ",");
            request.header(header.getName(), value);
        }
    }

    private static void setQueryParam(final UaiCallback callback, final HttpRequest request) {
        for (final UaiQueryParam queryParam : callback.getQueryParamList()) {
            request.queryString(queryParam.getName(), queryParam.getValueList());
        }
    }
}
