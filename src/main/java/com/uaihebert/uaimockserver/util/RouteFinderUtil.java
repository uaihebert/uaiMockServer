package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.validator.RequestValidator;
import io.undertow.server.HttpServerExchange;

public final class RouteFinderUtil {
    private RouteFinderUtil() {
    }

    public static UaiRoute findValidRoute(final UaiMockServerConfig uaiMockServerConfig, final HttpServerExchange exchange) {
        final String requestMethod = exchange.getRequestMethod().toString();
        final String requestURI = exchange.getRequestURI();

        final UaiRoute uaiRoute = uaiMockServerConfig.routeMap.get(requestMethod + "_" + requestURI);

        RequestValidator.validateRequest(uaiRoute, exchange, uaiMockServerConfig);

        return uaiRoute;
    }
}
