package com.uaihebert.uaimockserver.helper;

import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.factory.UaiRouteFactory;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRoute;

public final class UaiRouteHelper {
    private UaiRouteHelper() {
    }

    public static void editRoute(final UaiRouteDTO uaiRouteDTO) {
        final UaiRoute uaiRoute = UaiRouteFactory.create(uaiRouteDTO);
        UaiMockServerConfig.editRoute(uaiRoute);
    }
}