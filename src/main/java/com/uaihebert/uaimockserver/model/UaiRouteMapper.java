package com.uaihebert.uaimockserver.model;

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
        final String key = RouteMapKeyUtil.createKey(uaiRoute.getRequest().getMethod(), uaiRoute.getRequest().getPath());

        addRoute(key, uaiRoute);
    }

    private static void addRoute(final String key, final UaiRoute uaiRoute) {
        setInMapById(uaiRoute);
        setInMapByPath(key, uaiRoute);
    }

    private static void setInMapById(final UaiRoute uaiRoute) {
        ROUTE_MAP_BY_ID.put(uaiRoute.getId(), uaiRoute);
    }

    public static void editRoute(final UaiRoute uaiRoute) {
        final String key = RouteMapKeyUtil.createKey(uaiRoute.getRequest().getMethod(), uaiRoute.getRequest().getPath());

        deleteRoute(uaiRoute.getId());
        addRoute(key, uaiRoute);
        // todo we should add the route in the correct file
        UaiMockServerContext.INSTANCE.uaiMockServerConfig.getRouteList().add(uaiRoute);
    }

    public static void deleteRoute(final String routeId) {
        final UaiRoute routeToDelete = ROUTE_MAP_BY_ID.get(routeId);

        final String key = RouteMapKeyUtil.createKey(routeToDelete.getRequest().getMethod(), routeToDelete.getRequest().getPath());

        final Set<UaiRoute> uaiRouteList = getRouteList(key);
        uaiRouteList.remove(routeToDelete);

        ROUTE_MAP_BY_ID.remove(routeToDelete.getId());

        UaiMockServerContext.INSTANCE.uaiMockServerConfig.getRouteList().remove(routeToDelete);
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

    public static Set<UaiRoute> findRouteListByKey(final String requestKey) {
        return getRouteList(requestKey);
    }

    public static UaiRoute findById(final String routeId) {
        return ROUTE_MAP_BY_ID.get(routeId);
    }
}