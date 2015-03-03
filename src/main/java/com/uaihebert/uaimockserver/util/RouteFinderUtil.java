package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.model.UaiRouteMapper;
import com.uaihebert.uaimockserver.validator.RequestValidator;
import io.undertow.server.HttpServerExchange;

import java.util.HashSet;
import java.util.Set;

public final class RouteFinderUtil {
    private RouteFinderUtil() {
    }

    public static UaiRoute findValidRoute(final HttpServerExchange httpServerExchange) {
        final String requestKey = RouteMapKeyUtil.createKeyFromRequest(httpServerExchange);

        final Set<UaiRoute> uaiRouteList = UaiRouteMapper.findRouteListByKey(requestKey);

        final Set<UaiRoute> uaiRouteListWithEqualAttributes = findRoutesWithEqualAttributes(uaiRouteList, httpServerExchange);

        return RequestValidator.validateRequest(uaiRouteListWithEqualAttributes, httpServerExchange);
    }

    /**
     * This method will return the routes with the requests that has the same attributes.
     * <p/>
     * It will check for the same queryParam and header
     * <p/>
     * We can have an URLs like:
     * <p/>
     * http://uaimockserver.com?queryParam=A ----> return 201
     * http://uaimockserver.com?queryParam=B ----> return 204
     *
     * @param uaiRouteList       the route list that will be filtered
     * @param httpServerExchange the current request
     * @return a list of the found route.
     */
    private static Set<UaiRoute> findRoutesWithEqualAttributes(final Set<UaiRoute> uaiRouteList, final HttpServerExchange httpServerExchange) {
        final Set<UaiRoute> result = new HashSet<UaiRoute>();

        routLoop:
        for (UaiRoute uaiRoute : uaiRouteList) {
            for (UaiHeader uaiHeader : uaiRoute.getRequest().getRequiredHeaderList()) {
                if (!httpServerExchange.getRequestHeaders().contains(uaiHeader.getName())) {
                    continue routLoop;
                }
            }

            for (UaiQueryParam uaiQueryParam : uaiRoute.getRequest().getRequiredQueryParamList()) {
                if (!httpServerExchange.getQueryParameters().containsKey(uaiQueryParam.getName())) {
                    continue routLoop;
                }
            }

            result.add(uaiRoute);
        }

        return result;
    }
}