package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.configuration.ProjectConfiguration;
import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import io.undertow.server.HttpServerExchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public final class RouteFinderUtil {
    private static final String INVALID_DATA_MESSAGE = "It was not possible to validate the sent data against the routes in the config file. Check the log.";

    private static final String URI_NOT_FOUND_MESSAGE = "%nWe could not find the requested URI [%s] with the method [%s]. %n " +
            "Check the config file and try to find the mapping. A \\ in the end of the URI will affect the result. %n " +
            "Also check if all the required query param and/header were sent. %n";

    private RouteFinderUtil() {
    }

    /**
     * This method will return the route of the received request
     * It will check for the same queryParam and header
     * We can have URLs like:
     * http://uaimockserver.com?queryParam=A returns 201
     * http://uaimockserver.com?queryParam=B returns 204
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

        final String requestBody = extractBody(httpServerExchange);

        for (UaiRoute uaiRoute : orderedList) {
            final RequestValidatorFacade.RequestAnalysisResult validRequest = RequestValidatorFacade.isValidRequest(uaiRoute.getRequest(), httpServerExchange, requestBody);

            if (validRequest.isValid()) {
                return uaiRoute;
            }

            if (validRequest.isAbortTheRequest()) {
                break;
            }
        }

        Log.warnFormatted(INVALID_DATA_MESSAGE);

        throw new IllegalArgumentException(INVALID_DATA_MESSAGE);
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

    private static String extractBody(final HttpServerExchange exchange) {
        exchange.startBlocking();

        final Scanner scanner = new Scanner(exchange.getInputStream(), ProjectConfiguration.ENCODING.value).useDelimiter("\\A");

        if (!scanner.hasNext()) {
            return null;
        }

        return scanner.next();
    }
}