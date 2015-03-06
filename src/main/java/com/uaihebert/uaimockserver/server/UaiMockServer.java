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

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import com.uaihebert.uaimockserver.util.HttpServerUtil;
import io.undertow.Undertow;

/**
 * Class that will start/shutdown ServletServer implementation
 */
public final class UaiMockServer {

    private final Undertow httpServer;

    private UaiMockServer(final Undertow httpServer) {
        this.httpServer = httpServer;
    }

    public void shutdown() {
        UaiRouteRepository.clearData();
        httpServer.stop();
    }

    public static UaiMockServer start() {
        UaiMockServerContext.createInstance();

        return createServer();
    }

    public static UaiMockServer start(final String configurationFileName) {
        UaiMockServerContext.createInstance(configurationFileName);

        return createServer();
    }

    private static UaiMockServer createServer() {
        final Undertow undertow = HttpServerUtil.startHttpServer();

        return new UaiMockServer(undertow);
    }
}