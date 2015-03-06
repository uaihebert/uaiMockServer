package com.uaihebert.uaimockserver.repository;

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.FileUtil;

import java.util.List;
import java.util.Set;

public final class UaiRouteRepository {
    private UaiRouteRepository() {
    }

    public static void configureRouteData() {
        UaiRouteMapper.configureRouteData();
    }

    public static UaiRoute findById(final String routeId) {
        return UaiRouteMapper.findById(routeId);
    }

    public static void addFromFile(final UaiRoute uaiRoute) {
        UaiRouteMapper.addRoute(uaiRoute);
    }

    public static void create(final UaiRoute uaiRoute) {
        UaiRouteMapper.addRoute(uaiRoute);

        UaiMockServerContext.INSTANCE.addRoute(uaiRoute);

        flushData();
    }

    public static void update(final UaiRoute uaiRoute) {
        UaiRouteMapper.updateRoute(uaiRoute);

        flushData();
    }

    public static void delete(final String routeId) {
        final UaiRoute routeToDelete = UaiRouteMapper.deleteRoute(routeId);

        UaiMockServerContext.INSTANCE.deleteRoute(routeToDelete);

        flushData();
    }

    private static void flushData() {
        FileUtil.writeUpdatesToFile();
    }

    public static Set<UaiRoute> findRouteListByKey(final String requestKey) {
        return UaiRouteMapper.findRouteListByKey(requestKey);
    }

    public static List<UaiRoute> listAllRoutes() {
        return UaiRouteMapper.listAllRoutes();
    }

    public static void clearData() {
        UaiRouteMapper.resetRouteMapData();
    }
}