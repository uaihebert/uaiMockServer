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
import com.uaihebert.uaimockserver.factory.TypeSafeConfigFactory;
import com.uaihebert.uaimockserver.log.Log;
import com.uaihebert.uaimockserver.log.LogBuilder;
import com.uaihebert.uaimockserver.util.FileUtil;
import com.uaihebert.uaimockserver.util.RouteUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that will hold all the project configurations
 */
public class UaiMockServerConfig {
    public final UaiBasicServerConfiguration basicConfiguration;

    private static final Map<String, List<UaiRoute>> ROUTE_MAP = new HashMap<String, List<UaiRoute>>();

    private static final String CONFIGURATION_FILE_NAME = "uaiMockServer.config";

    public UaiMockServerConfig() {
        this(CONFIGURATION_FILE_NAME);
    }

    public UaiMockServerConfig(final String fileName) {

        final File file = FileUtil.findFile(fileName);
        final Config config = TypeSafeConfigFactory.loadConfiguration(file);

        createLog(config);

        basicConfiguration = new UaiBasicServerConfiguration(config);

        RouteUtil.configureRouteMap(config, this, file);

        Log.info(String.format("Configurations of the file [%s] was read with success", fileName));
    }

    private void createLog(final Config config) {
        final boolean fileLog = config.getBoolean(RootConstants.FILE_LOG.path);
        final boolean consoleLog = config.getBoolean(RootConstants.CONSOLE_LOG.path);

        LogBuilder.createInstance(fileLog, consoleLog);
    }

    public static List<UaiRoute> findRouteListByKey(final String requestKey) {
        return getRouteList(requestKey);
    }

    public static void addRoute(final String key, final UaiRoute uaiRoute) {
        final List<UaiRoute> uaiRouteList = getRouteList(key);

        uaiRouteList.add(uaiRoute);
    }

    private static List<UaiRoute> getRouteList(final String key) {
        if (ROUTE_MAP.containsKey(key)) {
            return ROUTE_MAP.get(key);
        }

        final List<UaiRoute> uaiRouteList = new ArrayList<UaiRoute>();

        ROUTE_MAP.put(key, uaiRouteList);

        return uaiRouteList;
    }

    public static List<UaiRoute> listAllRoutes() {
        final List<UaiRoute> resultList = new ArrayList<UaiRoute>();

        for (List<UaiRoute> uaiRouteList : ROUTE_MAP.values()) {
            resultList.addAll(uaiRouteList);
        }

        return resultList;
    }

    public static void resetRouteMap() {
        ROUTE_MAP.clear();
    }
}