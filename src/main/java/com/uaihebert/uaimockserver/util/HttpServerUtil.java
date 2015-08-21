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
package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.factory.undertow.PathHandlerFactory;
import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.handlers.PathHandler;

/**
 * This class is responsible for the servlet server instantiation
 */
public final class HttpServerUtil {
    private HttpServerUtil() {
    }

    public static Undertow startHttpServer() {
        final Undertow httpServer;

        try {
            final PathHandler path = PathHandlerFactory.create();

            httpServer = Undertow.builder()
                    .addHttpListener(UaiMockServerContext.getInstance().uaiMockServerConfig.getPort(), UaiMockServerContext.getInstance().uaiMockServerConfig.getHost())
                    .setHandler(path)
                    .setServerOption(UndertowOptions.URL_CHARSET, "utf-8")
                    .build();

            httpServer.start();
        } catch (final Exception ex) {
            throw new IllegalStateException("Could not start the uaiMockServer. " + ex.getMessage(), ex);
        }

        return httpServer;
    }
}