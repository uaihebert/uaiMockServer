package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.validator.RequestValidator;
import io.undertow.server.HttpServerExchange;

import java.util.List;

public final class RouteFinderUtil {
    private RouteFinderUtil() {
    }

    public static UaiRoute findValidRoute(final HttpServerExchange httpServerExchange) {
        final String requestKey = RouteMapKeyUtil.createKeyFromRequest(httpServerExchange);

        final List<UaiRoute> uaiRouteList = UaiMockServerConfig.findRouteListByKey(requestKey);

        final UaiRoute uaiRoute = findRoute(uaiRouteList, httpServerExchange);

        RequestValidator.validateRequest(uaiRoute, httpServerExchange);

        return uaiRoute;
    }

    private static UaiRoute findRoute(final List<UaiRoute> uaiRouteList, final HttpServerExchange httpServerExchange) {
        routLoop:
        for (UaiRoute uaiRoute : uaiRouteList) {
            for (UaiHeader uaiHeader : uaiRoute.uaiRequest.requiredHeaderList) {
                if (!httpServerExchange.getRequestHeaders().contains(uaiHeader.name)) {
                    continue routLoop;
                }
            }

            for (UaiQueryParam uaiQueryParam : uaiRoute.uaiRequest.requiredQueryParamList) {
                if (!httpServerExchange.getQueryParameters().containsKey(uaiQueryParam.name)) {
                    continue routLoop;
                }
            }

            return uaiRoute;
        }

        return null;
    }
}