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

    private static final String REQUIRED_HEADER_LOG_TEXT = "For the Route [%s] we found [%s] of required Headers";
    private static final String REQUIRED_QUERY_PARAM_LOG_TEXT = "For the Route [%s] we found [%s] of required QueryParameters";

    private static final String URI_NOT_FOUND_MESSAGE = "%nWe could not find the requested URI [%s] with the method [%s]. %n " +
            "Check the config file and try to find the mapping. A \\ in the end of the URI will affect the result. %n " +
            "Also check if all the required query param and/header were sent. %n";

    private static final String WRONG_HEADER_QUERY_PARAM_MESSAGE = "%nCheck the Body/QueryParam/Headers sent.  %n " +
            "We found the same headers but the values did not match";

    private RouteFinderUtil() {
    }

    // todo work with log. make sure that the header and queryParam log will be logged

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
     * @param httpServerExchange the current request
     * @return the route.
     */
    public static UaiRoute findValidRoute(final HttpServerExchange httpServerExchange) {
        final List<UaiRoute> orderedList = getOrderedRouteByKey(httpServerExchange);

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

    private static List<UaiRoute> getOrderedRouteByKey(final HttpServerExchange httpServerExchange) {
        final Set<UaiRoute> uaiRouteList = UaiRouteRepository.findRouteListByKey(httpServerExchange);

        final List<UaiRoute> listToOrder = filterRoutesIfNeeded(new ArrayList<UaiRoute>(uaiRouteList));

        Collections.sort(listToOrder, new RouteComparator());

        return listToOrder;
    }

    private static List<UaiRoute> filterRoutesIfNeeded(final List<UaiRoute> orderedList) {
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

    private static boolean hasRequiredHeader(final List<UaiRoute> orderedList) {
        for (UaiRoute uaiRoute : orderedList) {
            final UaiRequest request = uaiRoute.getRequest();
            if (!request.getRequiredHeaderList().isEmpty()) {
                return true;
            }
        }

        return false;
    }
}