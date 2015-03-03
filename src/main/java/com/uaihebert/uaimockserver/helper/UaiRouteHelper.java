package com.uaihebert.uaimockserver.helper;

import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.factory.UaiRouteFactory;
import com.uaihebert.uaimockserver.model.UaiMockServerContext;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.model.UaiRouteMapper;
import com.uaihebert.uaimockserver.util.FileUtil;

public final class UaiRouteHelper {
    private UaiRouteHelper() {
    }

    public static void editRoute(final UaiRouteDTO uaiRouteDTO) {
        final UaiRoute uaiRoute = UaiRouteMapper.findById(uaiRouteDTO.getId());

        UaiRouteFactory.update(uaiRoute, uaiRouteDTO);

        UaiRouteMapper.editRoute(uaiRoute);

        FileUtil.writeUpdatesToFile();
    }

    public static void createRoute(final UaiRouteDTO uaiRouteDTO) {
        final UaiRoute uaiRoute = UaiRouteFactory.create(uaiRouteDTO);
        UaiRouteMapper.addRoute(uaiRoute);
        UaiMockServerContext.INSTANCE.uaiMockServerConfig.getRouteList().add(uaiRoute);

        FileUtil.writeUpdatesToFile();
    }

    public static void deleteRoute(final String routeId) {
        UaiRouteMapper.deleteRoute(routeId);

        FileUtil.writeUpdatesToFile();
    }
}