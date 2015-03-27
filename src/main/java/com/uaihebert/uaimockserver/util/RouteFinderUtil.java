package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import io.undertow.server.HttpServerExchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class RouteFinderUtil {
    private static final String INVALID_DATA_MESSAGE = "Whe found the request URI, but some data was missing. Check your: HEADER," +
            "QUERY_PARAM,CONTENT-TYPE,BODY";

    private static final String URI_NOT_FOUND_MESSAGE = "%nWe could not find the requested URI [%s] with the method [%s]. %n " +
            "Check the config file and try to find the mapping. A \\ in the end of the URI will affect the result. %n " +
            "Also check if all the required query param and/header were sent. %n";

    private RouteFinderUtil() {
    }

    /**
     * This method will return the route of the received request
     * <p/>
     * It will check for the same queryParam and header
     * <p/>
     * We can have URLs like:
     * <p/>
     * http://uaimockserver.com?queryParam=A ----> return 201
     * http://uaimockserver.com?queryParam=B ----> return 204
     *
     * @param httpServerExchange the current request
     * @return the route.
     */
    public static UaiRoute findValidRoute(final HttpServerExchange httpServerExchange) {
        final List<UaiRoute> orderedList = getSortedRouteByKey(httpServerExchange);

        if (orderedList.isEmpty()) {
            final String errorText = String.format(URI_NOT_FOUND_MESSAGE, httpServerExchange.getRequestURI(), httpServerExchange.getRequestMethod());
            ExceptionUtil.logBeforeThrowing(new IllegalArgumentException(errorText));
        }

        for (UaiRoute uaiRoute : orderedList) {
            if (RequestValidatorFacade.isValidRequest(uaiRoute.getRequest(), httpServerExchange)) {
                return uaiRoute;
            }
        }

        final String errorText = String.format(INVALID_DATA_MESSAGE);

        Log.warn(errorText);

        throw new IllegalArgumentException(errorText);
    }

    private static List<UaiRoute> getSortedRouteByKey(final HttpServerExchange httpServerExchange) {
        final Set<UaiRoute> uaiRouteList = UaiRouteRepository.findRouteListByKey(httpServerExchange);

        final List<UaiRoute> listToOrder = filterRoutesIfNeeded(new ArrayList<UaiRoute>(uaiRouteList));

        Collections.sort(listToOrder, new RouteComparator());

        return listToOrder;
    }

    private static List<UaiRoute> filterRoutesIfNeeded(final List<UaiRoute> orderedList) {
        final List<UaiRoute> uaiRouteList = filterByHeader(orderedList);
        
        return filterByQueryParam(uaiRouteList);
    }

    private static List<UaiRoute> filterByHeader(final List<UaiRoute> orderedList) {
        boolean hasRequiredHeader = hasRequiredHeader(orderedList);

        if (!hasRequiredHeader) {
            return orderedList;
        }

        final List<UaiRoute> uaiRouteList = new ArrayList<UaiRoute>();

        for (UaiRoute uaiRoute : orderedList) {
            if (uaiRoute.getRequest().getRequiredHeaderList().isEmpty()) {
                continue;
            }

            uaiRouteList.add(uaiRoute);
        }

        return uaiRouteList;
    }

    private static List<UaiRoute> filterByQueryParam(final List<UaiRoute> orderedList) {
        boolean hasRequiredQueryParam = hasRequiredQueryParam(orderedList);

        if (!hasRequiredQueryParam) {
            return orderedList;
        }

        final List<UaiRoute> uaiRouteList = new ArrayList<UaiRoute>();

        for (UaiRoute uaiRoute : orderedList) {
            if (uaiRoute.getRequest().getRequiredQueryParamList().isEmpty()) {
                continue;
            }

            uaiRouteList.add(uaiRoute);
        }

        return uaiRouteList;
    }

    private static boolean hasRequiredHeader(final List<UaiRoute> orderedList) {
        for (UaiRoute uaiRoute : orderedList) {
            final UaiRequest request = uaiRoute.getRequest();
            if (!request.getRequiredHeaderList().isEmpty()) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasRequiredQueryParam(final List<UaiRoute> orderedList) {
        for (UaiRoute uaiRoute : orderedList) {
            final UaiRequest request = uaiRoute.getRequest();
            if (!request.getRequiredQueryParamList().isEmpty()) {
                return true;
            }
        }

        return false;
    }
}