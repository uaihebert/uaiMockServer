/*
 * Copyright 2015 uaiHebert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
package com.uaihebert.uaimockserver.server;

import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiResponse;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.RouteFinderUtil;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;

/**
 * This class is the responsible for handling a incoming request.
 * The request will be validated and if it is correctly configured its response will be sent.
 * If there request is not found or have any kind of error, an InternalServerError will be sent
 */
public class UaiMockServerHandler implements HttpHandler {
    private final UaiMockServerConfig config;

    public UaiMockServerHandler(final UaiMockServerConfig config) {
        super();
        this.config = config;
    }

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        config.log.infoFormatted("Incoming request: method [%s] URI [%s]", exchange.getRequestMethod(), exchange.getRequestURI());

        final UaiRoute uaiRoute = RouteFinderUtil.findValidRoute(config, exchange);

        final UaiResponse uaiResponse = uaiRoute.uaiResponse;

        config.log.infoFormatted("Response that will be sent: [%s]", uaiResponse);

        prepareResponse(exchange, uaiResponse);
    }

    private void prepareResponse(final HttpServerExchange exchange, final UaiResponse uaiResponse) {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, uaiResponse.contentType);

        exchange.setResponseCode(uaiResponse.statusCode);

        setResponseHeaders(uaiResponse, exchange);

        if (uaiResponse.body != null) {
            exchange.getResponseSender().send(uaiResponse.body);
        }
    }

    private void setResponseHeaders(final UaiResponse uaiResponse, final HttpServerExchange exchange) {
        for (UaiHeader uaiHeader : uaiResponse.headerList) {
            for (String value : uaiHeader.valueList) {
                exchange.getResponseHeaders().add(new HttpString(uaiHeader.name), value);
            }
        }
    }
}