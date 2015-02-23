package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.validator.RequestValidator;
import io.undertow.server.HttpServerExchange;

import java.util.ArrayList;
import java.util.List;

public final class RouteFinderUtil {
    private RouteFinderUtil() {
    }

    public static UaiRoute findValidRoute(final HttpServerExchange httpServerExchange) {
        final String requestKey = RouteMapKeyUtil.createKeyFromRequest(httpServerExchange);

        final List<UaiRoute> uaiRouteList = UaiMockServerConfig.findRouteListByKey(requestKey);

        final List<UaiRoute> uaiRouteListWithEqualAttributes = findRoutesWithEqualAttributes(uaiRouteList, httpServerExchange);

        return RequestValidator.validateRequest(uaiRouteListWithEqualAttributes, httpServerExchange);
    }

    /**
     * This method will return the routes with the requests that has the same attributes.
     *
     * It will check for the same queryParam and header
     *
     * We can have an URLs like:
     *
     *    http://uaimockserver.com?queryParam=A ----> return 201
     *    http://uaimockserver.com?queryParam=B ----> return 204
     *
     * @param uaiRouteList the route list that will be filtered
     * @param httpServerExchange the current request
     * @return a list of the found route.
     */
    private static List<UaiRoute> findRoutesWithEqualAttributes(final List<UaiRoute> uaiRouteList, final HttpServerExchange httpServerExchange) {
        final List<UaiRoute> result = new ArrayList<UaiRoute>();

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

            result.add(uaiRoute);
        }

        return result;
    }
}