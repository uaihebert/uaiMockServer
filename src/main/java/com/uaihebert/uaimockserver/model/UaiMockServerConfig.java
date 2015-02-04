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
package com.uaihebert.uaimockserver.model;

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.constants.RootConstants;
import com.uaihebert.uaimockserver.factory.LogFactory;
import com.uaihebert.uaimockserver.factory.TypeSafeConfigFactory;
import com.uaihebert.uaimockserver.log.Log;
import com.uaihebert.uaimockserver.util.RouteUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that will hold all the project configurations
 */
public class UaiMockServerConfig {
    public final int port;

    public final Log log;

    public final String host;
    public final String context;
    public final String defaultContentType;

    public final boolean fileLog;
    public final boolean consoleLog;

    public final Map<String, UaiRoute> routeMap = new HashMap<String, UaiRoute>();

    private static final String CONFIGURATION_FILE_NAME = "uaiMockServer.config";

    public UaiMockServerConfig() {
        this(CONFIGURATION_FILE_NAME);
    }

    public UaiMockServerConfig(final String fileName) {
        final Config config = TypeSafeConfigFactory.loadConfiguration(fileName);

        fileLog = config.getBoolean(RootConstants.FILE_LOG.path);
        consoleLog = config.getBoolean(RootConstants.CONSOLE_LOG.path);

        log = LogFactory.create(this);

        port = config.getInt(RootConstants.PORT.path);

        host = config.getString(RootConstants.HOST.path);
        context = config.getString(RootConstants.CONTEXT.path);
        defaultContentType = config.getString(RootConstants.DEFAULT_CONTENT_TYPE_RESPONSE.path);

        RouteUtil.configureRouteMap(config, this);

        log.info(String.format("Configurations of the file [%s] was read with success", fileName));
    }
}