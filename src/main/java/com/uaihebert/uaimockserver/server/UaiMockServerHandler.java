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

import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.log.gui.UaiWebSocketLogManager;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.RouteFinderUtil;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This class is the responsible for handling a incoming request.
 * The request will be validated and if it is correctly configured its response will be sent.
 * If there request is not found or have any kind of error, an InternalServerError will be sent
 */
public class UaiMockServerHandler implements HttpHandler {
    private final ResponseHandler responseHandler = new ResponseHandler();
    private final RequestHandler requestHandler = new RequestHandler();

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        // firefox will invoke this URL
        if ("/favicon.ico".equals(exchange.getRequestPath())) {
            sendFavIconInResponse(exchange);
            return;
        }

        UaiWebSocketLogManager.start(exchange);

        try {
            Log.infoFormatted("Incoming request: method [%s] URI [%s]", exchange.getRequestMethod(), exchange.getRequestURI());

            final UaiRoute uaiRoute = RouteFinderUtil.findValidRoute(exchange);

            requestHandler.processRequest(uaiRoute);

            responseHandler.process(exchange, uaiRoute.getResponse());

            UaiWebSocketLogManager.logResponse(uaiRoute.getResponse());
        } catch (RuntimeException ex) {
            UaiWebSocketLogManager.exceptionDetected(ex.getMessage());
            throw ex;
        }finally {
            UaiWebSocketLogManager.log();
        }
    }

    private void sendFavIconInResponse(final HttpServerExchange exchange) throws IOException {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "image/png");

        exchange.startBlocking();

        writeFavIco(exchange.getOutputStream());
    }

    public void writeFavIco(final OutputStream outputStream) throws IOException {
        final BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/favicon.png"));

        ImageIO.write(bufferedImage, "png", outputStream);

        outputStream.close();
    }
}