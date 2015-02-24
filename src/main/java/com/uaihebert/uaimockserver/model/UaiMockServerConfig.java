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
    private static final Map<String, Set<UaiRoute>> ROUTE_MAP_BY_PATH = new HashMap<String, Set<UaiRoute>>();

    private static final String CONFIGURATION_FILE_NAME = "uaiMockServer.config";

    private UaiMockServerConfig() {
    }

    public static void createInstance() {
        createInstance(CONFIGURATION_FILE_NAME);
    }

    // todo tratar caso quando usuário trocar o metodo do request GET -> POST
    // todo tratar caso quando usuário o PATH
    // todo create a test with another context
    // todo in the dialog page, send to the dialog a clone of the object, and not the real one
    public static void createInstance(final String fileName) {
        final File file = FileUtil.findFile(fileName);
        final Config config = TypeSafeConfigFactory.loadConfiguration(file);

        createLog(config);

        UaiBasicServerConfiguration.createInstance(config);

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

    public static void addRoute(final String key, final UaiRoute uaiRoute) {
        setInMapByPath(key, uaiRoute);
    }

    public static void editRoute(final UaiRoute uaiRoute) {
        final String key = RouteMapKeyUtil.createKey(uaiRoute.uaiRequest.method, uaiRoute.uaiRequest.path);

        deleteOldRoute(key, uaiRoute);
        addRoute(key, uaiRoute);
    }

    private static void deleteOldRoute(final String key, final UaiRoute uaiRoute) {
        final Set<UaiRoute> uaiRouteList = getRouteList(key);
        uaiRouteList.remove(uaiRoute);
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