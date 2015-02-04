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

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.server.UaiMockServerHandler;
import io.undertow.Undertow;

/**
 * This class is responsible of the servlet server instantiation
 */
public final class HttpServerUtil {
    private HttpServerUtil() {
    }

    public static Undertow startHttpServer(final UaiMockServerConfig config) {
        final Undertow httpServer = Undertow.builder()
                .addHttpListener(config.port, config.host)
                .setHandler(new UaiMockServerHandler(config))
                .build();

        try{
            httpServer.start();
        } catch (final RuntimeException ex) {
            throw new IllegalStateException("Could not start the uaiMockServer.", ex);
        }

        return httpServer;
    }
}