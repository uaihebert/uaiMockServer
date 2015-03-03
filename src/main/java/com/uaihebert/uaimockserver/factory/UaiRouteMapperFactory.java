package com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiMockServerContext;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.model.UaiRouteMapper;

import java.util.List;

public final class UaiRouteMapperFactory {
    private UaiRouteMapperFactory() {
    }

    public static void configureRouteMapper() {
        configureMainFile();

        configureSecondaryFiles();
    }

    private static void configureMainFile() {
        final List<UaiRoute> routeList = UaiMockServerContext.INSTANCE.uaiMockServerConfig.getRouteList();

        for (UaiRoute uaiRoute : routeList) {
            UaiRouteMapper.addRoute(uaiRoute);
        }
    }

    private static void configureSecondaryFiles() {
        for (UaiMockServerConfig secondaryConfigFile : UaiMockServerContext.INSTANCE.secondaryMappingList) {
            for (UaiRoute uaiRoute : secondaryConfigFile.getRouteList()) {
                UaiRouteMapper.addRoute(uaiRoute);
            }
        }
    }
}