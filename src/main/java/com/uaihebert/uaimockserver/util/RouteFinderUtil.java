package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import com.uaihebert.uaimockserver.validator.RequestValidator;
import io.undertow.server.HttpServerExchange;

import java.util.HashSet;
import java.util.Set;

public final class RouteFinderUtil {
    private static final String REQUIRED_HEADER_NOT_FOUND = "The required header [%s] was not found in the request";
    private static final String REQUIRED_QUERY_PARAM_NOT_FOUND = "The required header [%s] was not found in the request";

    private static final String REQUIRED_HEADER_LOG_TEXT = "For the Route [%s] we found [%s] of required Headers";
    private static final String REQUIRED_QUERY_PARAM_LOG_TEXT = "For the Route [%s] we found [%s] of required QueryParameters";

    private RouteFinderUtil() {
    }

    public static UaiRoute findValidRoute(final HttpServerExchange httpServerExchange) {
        final String requestKey = RouteMapKeyUtil.createKeyFromRequest(httpServerExchange);

        final Set<UaiRoute> uaiRouteList = UaiRouteRepository.findRouteListByKey(requestKey);

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

        for (UaiRoute uaiRoute : uaiRouteList) {
            final UaiRequest uaiRequest = uaiRoute.getRequest();

            logTotalOfLists(httpServerExchange, uaiRequest);

            if (hasErrorInTheMetaData(httpServerExchange, uaiRequest)) {
                continue;
            }

            result.add(uaiRoute);
        }

        return result;
    }

    private static boolean hasErrorInTheMetaData(final HttpServerExchange httpServerExchange, final UaiRequest uaiRequest) {
        return validateHeaders(httpServerExchange, uaiRequest) || validateQueryParam(httpServerExchange, uaiRequest);
    }

    private static void logTotalOfLists(final HttpServerExchange httpServerExchange, final UaiRequest uaiRequest) {
        final String requestKey = RouteMapKeyUtil.createKeyFromRequest(httpServerExchange);

        Log.infoFormatted(REQUIRED_HEADER_LOG_TEXT, requestKey, uaiRequest.getRequiredHeaderList().size());
        Log.infoFormatted(REQUIRED_QUERY_PARAM_LOG_TEXT, requestKey,  uaiRequest.getRequiredQueryParamList().size());
    }

    private static boolean validateQueryParam(final HttpServerExchange httpServerExchange, final UaiRequest uaiRequest) {
        boolean hasError = false;

        for (UaiQueryParam uaiQueryParam : uaiRequest.getRequiredQueryParamList()) {
            if (!httpServerExchange.getQueryParameters().containsKey(uaiQueryParam.getName())) {
                Log.warn(REQUIRED_HEADER_NOT_FOUND, uaiQueryParam.getName());
                hasError = true;
            }
        }

        return hasError;
    }

    private static boolean validateHeaders(final HttpServerExchange httpServerExchange, final UaiRequest uaiRequest) {
        boolean hasError = false;

        for (UaiHeader uaiHeader : uaiRequest.getRequiredHeaderList()) {
            if (!httpServerExchange.getRequestHeaders().contains(uaiHeader.getName())) {
                Log.warn(REQUIRED_QUERY_PARAM_NOT_FOUND, uaiHeader.getName());
                hasError = true;
            }
        }

        return hasError;
    }
}