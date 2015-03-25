package com.uaihebert.uaimockserver.repository;

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.RouteMapKeyUtil;
import com.uaihebert.uaimockserver.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class UaiRouteMapper {
    public static final String ALL_PROJECT = "ALL PROJECTS";

    private static final Map<String, UaiRoute> ROUTE_MAP_BY_ID = new HashMap<String, UaiRoute>();
    private static final Map<String, Set<UaiRoute>> ROUTE_MAP_BY_PATH = new HashMap<String, Set<UaiRoute>>();
    private static final Map<String, Set<UaiRoute>> ROUTE_MAP_BY_APPLICATION = new HashMap<String, Set<UaiRoute>>();

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
        loadRouteByProject();
    }

    public static void loadMapByKey() {
        synchronized (ROUTE_MAP_BY_PATH) {
            ROUTE_MAP_BY_PATH.clear();

            for (UaiRoute uaiRoute : ROUTE_MAP_BY_ID.values()) {
                final String key = RouteMapKeyUtil.createKey(uaiRoute.getRequest().method, uaiRoute.getRequest().path);
                setInMapByPath(key, uaiRoute);
            }
        }
    }

    public static UaiRoute deleteRoute(final String routeId) {
        final UaiRoute routeToDelete = ROUTE_MAP_BY_ID.get(routeId);

        final String key = RouteMapKeyUtil.createKey(routeToDelete.getRequest().method, routeToDelete.getRequest().path);

        final Set<UaiRoute> uaiRouteList = getRouteList(key);
        uaiRouteList.remove(routeToDelete);

        ROUTE_MAP_BY_ID.remove(routeToDelete.getId());

        loadMapByKey();
        loadRouteByProject();

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

    public static List<UaiRoute> listAllRoutes(final String selectedProject) {
        if (!ROUTE_MAP_BY_APPLICATION.containsKey(selectedProject)) {
            return extractWithoutFilter();
        }

        return new ArrayList<UaiRoute>(ROUTE_MAP_BY_APPLICATION.get(selectedProject));
    }

    private static List<UaiRoute> extractWithoutFilter() {
        final List<UaiRoute> resultList = new ArrayList<UaiRoute>();

        for (Set<UaiRoute> uaiRouteList : ROUTE_MAP_BY_PATH.values()) {
            resultList.addAll(uaiRouteList);
        }

        return resultList;
    }

    public static void resetRouteMapData() {
        ROUTE_MAP_BY_ID.clear();
        ROUTE_MAP_BY_PATH.clear();
        ROUTE_MAP_BY_APPLICATION.clear();
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

    public static void loadRouteByProject() {
        ROUTE_MAP_BY_APPLICATION.clear();

        final List<UaiRoute> uaiRouteList = UaiRouteRepository.listAllRoutes();
        ROUTE_MAP_BY_APPLICATION.put(ALL_PROJECT, new HashSet<UaiRoute>(uaiRouteList));

        for (UaiRoute uaiRoute : uaiRouteList) {
            final String project = uaiRoute.getProject();

            if (StringUtils.isBlank(project)) {
                continue;
            }

            final Set<UaiRoute> uaiRouteSet;

            if (!ROUTE_MAP_BY_APPLICATION.containsKey(project)) {
                uaiRouteSet = new HashSet<UaiRoute>();
                ROUTE_MAP_BY_APPLICATION.put(project, uaiRouteSet);
            } else {
                uaiRouteSet = ROUTE_MAP_BY_APPLICATION.get(project);
            }

            uaiRouteSet.add(uaiRoute);
        }
    }

    public static List<String> extractProjectFromRoutes() {
        final List<String> projectList = new ArrayList<String>(ROUTE_MAP_BY_APPLICATION.keySet());

        projectList.remove(ALL_PROJECT);

        return projectList;
    }
}