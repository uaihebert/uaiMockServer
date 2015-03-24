package com.uaihebert.uaimockserver.repository;

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.RouteMapKeyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class UaiRouteMapper {
    private static final Map<String, UaiRoute> ROUTE_MAP_BY_ID = new HashMap<String, UaiRoute>();
    private static final Map<String, Set<UaiRoute>> ROUTE_MAP_BY_PATH = new HashMap<String, Set<UaiRoute>>();

    private UaiRouteMapper() {
    }

    public static void addRoute(final UaiRoute uaiRoute) {
        setInMapById(uaiRoute);
        loadMapByKey();
    }

    private static void setInMapById(final UaiRoute uaiRoute) {
        ROUTE_MAP_BY_ID.put(uaiRoute.getId(), uaiRoute);
    }

    public static void updateRoute() {
        loadMapByKey();
    }

    public static void loadMapByKey() {
        synchronized (ROUTE_MAP_BY_PATH) {
            ROUTE_MAP_BY_PATH.clear();

            for (UaiRoute uaiRoute : ROUTE_MAP_BY_ID.values()) {
                final String key = RouteMapKeyUtil.createKey(uaiRoute.getRequest().getMethod(), uaiRoute.getRequest().getPath());
                setInMapByPath(key, uaiRoute);
            }
        }
    }

    public static UaiRoute deleteRoute(final String routeId) {
        final UaiRoute routeToDelete = ROUTE_MAP_BY_ID.get(routeId);

        final String key = RouteMapKeyUtil.createKey(routeToDelete.getRequest().getMethod(), routeToDelete.getRequest().getPath());

        final Set<UaiRoute> uaiRouteList = getRouteList(key);
        uaiRouteList.remove(routeToDelete);

        ROUTE_MAP_BY_ID.remove(routeToDelete.getId());

        return routeToDelete;
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

    public static void resetRouteMapData() {
        ROUTE_MAP_BY_ID.clear();
        ROUTE_MAP_BY_PATH.clear();
    }

    public static Set<UaiRoute> findRouteListByKey(final String requestKey) {
        return getRouteList(requestKey);
    }

    public static UaiRoute findById(final String routeId) {
        return ROUTE_MAP_BY_ID.get(routeId);
    }

    public static void configureRouteData() {
        configureMainFile();

        configureSecondaryFiles();
    }

    private static void configureMainFile() {
        final List<UaiRoute> routeList = UaiMockServerContext.getInstance().uaiMockServerConfig.getRouteList();

        for (UaiRoute uaiRoute : routeList) {
            UaiRouteRepository.addFromFile(uaiRoute);
        }
    }

    private static void configureSecondaryFiles() {
        for (UaiMockServerConfig secondaryConfigFile : UaiMockServerContext.getInstance().secondaryMappingList) {
            for (UaiRoute uaiRoute : secondaryConfigFile.getRouteList()) {
                UaiRouteRepository.addFromFile(uaiRoute);
            }
        }
    }
}