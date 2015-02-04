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

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.constants.RootConstants;
import com.uaihebert.uaimockserver.factory.TypeSafeConfigFactory;
import com.uaihebert.uaimockserver.factory.UaiRouteFactory;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.model.UaiRoute;

import java.util.List;

/**
 * Class with methods that will help to handle the Route
 */
public final class RouteUtil {

    private RouteUtil() {
    }

    public static void configureRouteMap(final Config config, final UaiMockServerConfig uaiMockServerConfig) {
        configureMainFileRoute(config, uaiMockServerConfig);
        configureRouteFileMap(config, uaiMockServerConfig);
    }

    /**
     * Are the files with only a mapping route
     * @param config the TypeSafe config file loaded with the Main File
     * @param uaiMockServerConfig the project configuration with the ROOT configurations already set
     */
    private static void configureRouteFileMap(final Config config, final UaiMockServerConfig uaiMockServerConfig) {
        final List<String> mappingRoutesFileList = ConfigKeyUtil.getStringListSilently(RootConstants.MAPPING_ROUTES_FILE_LIST.path, config);

        for (String fileName : mappingRoutesFileList) {
            final Config mappedFileConfiguration = TypeSafeConfigFactory.loadConfiguration(fileName);

            extractRoute(uaiMockServerConfig, mappedFileConfiguration);
        }
    }

    private static void extractRoute(final UaiMockServerConfig uaiMockServerConfig, final Config config) {
        final List<? extends Config> routeConfigList = ConfigKeyUtil.getConfigListSilently(RootConstants.ROUTES.path, config);

        for (Config routeConfig : routeConfigList) {
            final UaiRoute uaiRoute = UaiRouteFactory.create(routeConfig, uaiMockServerConfig);
            final UaiRequest uaiRequest = uaiRoute.uaiRequest;

            uaiMockServerConfig.routeMap.put(RouteMapKeyUtil.createKeyForMap(uaiRequest, uaiMockServerConfig), uaiRoute);
        }
    }

    /**
     * Main File route is the file that will be used with the project bootstrap
     * @param config the TypeSafe config file loaded with the Main File
     * @param uaiMockServerConfig the project configuration with the ROOT configurations already set
     */
    private static void configureMainFileRoute(final Config config, final UaiMockServerConfig uaiMockServerConfig) {
        extractRoute(uaiMockServerConfig, config);
    }
}