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
import com.uaihebert.uaimockserver.factory.TypeSafeConfigFactory;
import com.uaihebert.uaimockserver.util.RouteUtil;

import java.util.*;

/**
 * Class that will hold all the project configurations
 */
public class UaiMockServerConfig {
    public final UaiBasicServerConfiguration basicConfiguration;

    private static final Map<String, UaiRoute> ROUTE_MAP = new HashMap<String, UaiRoute>();

    private static final String CONFIGURATION_FILE_NAME = "uaiMockServer.config";

    public UaiMockServerConfig() {
        this(CONFIGURATION_FILE_NAME);
    }

    public UaiMockServerConfig(final String fileName) {
        final Config config = TypeSafeConfigFactory.loadConfiguration(fileName);

        basicConfiguration = new UaiBasicServerConfiguration(config);

        RouteUtil.configureRouteMap(config, this);

        basicConfiguration.log.info(String.format("Configurations of the file [%s] was read with success", fileName));
    }

    public static UaiRoute findRoute(final String key) {
        return ROUTE_MAP.get(key);
    }

    public static void addRoute(final String key, final UaiRoute uaiRoute){
        ROUTE_MAP.put(key, uaiRoute);
    }

    public static List<UaiRoute> listAllRoutes() {
        return Collections.unmodifiableList(new ArrayList<UaiRoute>(ROUTE_MAP.values()));
    }
}