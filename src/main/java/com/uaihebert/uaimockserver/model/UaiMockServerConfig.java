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
import com.uaihebert.uaimockserver.util.RouteMapKeyUtil;
import com.uaihebert.uaimockserver.util.RouteUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class that will hold all the project configurations
 */
public final class UaiMockServerConfig {
    private static final Map<String, UaiRoute> ROUTE_MAP_BY_ID = new HashMap<String, UaiRoute>();
    private static final Map<String, Set<UaiRoute>> ROUTE_MAP_BY_PATH = new HashMap<String, Set<UaiRoute>>();

    private static final String CONFIGURATION_FILE_NAME = "uaiMockServer.config";

    private UaiMockServerConfig() {
    }

    public static void createInstance() {
        createInstance(CONFIGURATION_FILE_NAME);
    }

    public static void createInstance(final String fileName) {
        final File file = FileUtil.findFile(fileName);
        final Config config = TypeSafeConfigFactory.loadConfiguration(file);

        createLog(config);

        UaiBasicServerConfiguration.createInstance(config, file.getAbsolutePath());

        RouteUtil.configureRouteMap(config, file);

        Log.info(String.format("Configurations of the file [%s] was read with success", fileName));
    }

    private static void createLog(final Config config) {
        final boolean fileLog = config.getBoolean(RootConstants.FILE_LOG.path);
        final boolean consoleLog = config.getBoolean(RootConstants.CONSOLE_LOG.path);

        LogBuilder.createInstance(fileLog, consoleLog);
    }

    public static Set<UaiRoute> findRouteListByKey(final String requestKey) {
        return getRouteList(requestKey);
    }

    public static void addRoute(final UaiRoute uaiRoute) {
        final String key = RouteMapKeyUtil.createKey(uaiRoute.uaiRequest.method, uaiRoute.uaiRequest.path);

        addRoute(key, uaiRoute);
    }

    public static void addRoute(final String key, final UaiRoute uaiRoute) {
        setInMapById(uaiRoute);
        setInMapByPath(key, uaiRoute);
    }

    private static void setInMapById(final UaiRoute uaiRoute) {
        ROUTE_MAP_BY_ID.put(uaiRoute.id, uaiRoute);
    }

    public static void editRoute(final UaiRoute uaiRoute) {
        final String key = RouteMapKeyUtil.createKey(uaiRoute.uaiRequest.method, uaiRoute.uaiRequest.path);

        deleteRoute(uaiRoute.id);
        addRoute(key, uaiRoute);
    }

    public static void deleteRoute(final String routeId) {
        final UaiRoute routeToDelete = ROUTE_MAP_BY_ID.get(routeId);

        final String key = RouteMapKeyUtil.createKey(routeToDelete.uaiRequest.method, routeToDelete.uaiRequest.path);

        final Set<UaiRoute> uaiRouteList = getRouteList(key);
        uaiRouteList.remove(routeToDelete);

        ROUTE_MAP_BY_ID.remove(routeToDelete.id);
    }

    private static void setInMapByPath(final String key, final UaiRoute uaiRoute) {
        final Set<UaiRoute> uaiRouteList = getRouteList(key);

        uaiRouteList.add(uaiRoute);
    }

    private static Set<UaiRoute> getRouteList(final String key) {
        if (ROUTE_MAP_BY_PATH.containsKey(key)) {
            return ROUTE_MAP_BY_PATH.get(key);
        }

        final Set<UaiRoute> uaiRouteList = new HashSet<UaiRoute>();

        ROUTE_MAP_BY_PATH.put(key, uaiRouteList);

        return uaiRouteList;
    }

    public static List<UaiRoute> listAllRoutes() {
        final List<UaiRoute> resultList = new ArrayList<UaiRoute>();

        for (Set<UaiRoute> uaiRouteList : ROUTE_MAP_BY_PATH.values()) {
            resultList.addAll(uaiRouteList);
        }

        return resultList;
    }

    public static void resetRouteMap() {
        ROUTE_MAP_BY_PATH.clear();
    }
}