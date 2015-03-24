package com.uaihebert.uaimockserver.repository;

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.FileUtil;

import java.util.List;
import java.util.Set;

public final class UaiRouteRepository {
    private static final String ROUTE_FOUND_TEXT = "Found [%s] route(s) for the requested METHOD_URL [%s]";

    private UaiRouteRepository() {
    }

    public static void configureRouteData() {
        UaiRouteMapper.configureRouteData();
        UaiRouteMapper.loadMapByKey();
    }

    public static UaiRoute findById(final String routeId) {
        return UaiRouteMapper.findById(routeId);
    }

    public static void addFromFile(final UaiRoute uaiRoute) {
        UaiRouteMapper.addRoute(uaiRoute);
    }

    public static void create(final UaiRoute uaiRoute) {
        UaiRouteMapper.addRoute(uaiRoute);

        UaiMockServerContext.getInstance().addRoute(uaiRoute);

        flushData();
    }

    public static void update() {
        UaiRouteMapper.updateRoute();

        flushData();
    }

    public static void delete(final String routeId) {
        final UaiRoute routeToDelete = UaiRouteMapper.deleteRoute(routeId);

        UaiMockServerContext.getInstance().deleteRoute(routeToDelete);

        flushData();
    }

    private static void flushData() {
        FileUtil.writeUpdatesToFile();
    }

    public static Set<UaiRoute> findRouteListByKey(final String requestKey) {
        final Set<UaiRoute> routeList = UaiRouteMapper.findRouteListByKey(requestKey);

        Log.infoFormatted(ROUTE_FOUND_TEXT, routeList.size(), requestKey);

        return routeList;
    }

    public static List<UaiRoute> listAllRoutes() {
        return UaiRouteMapper.listAllRoutes();
    }

    public static int count() {
        return listAllRoutes().size();
    }

    public static void clearData() {
        UaiRouteMapper.resetRouteMapData();
    }
}