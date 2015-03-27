package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.model.UaiRoute;

import java.util.Comparator;

/**
 * This will order the route with more queryParam and header first
 */
public class RouteComparator implements Comparator<UaiRoute> {

    @Override
    public int compare(final UaiRoute firstRoute, final UaiRoute secondRoute) {
        final UaiRequest firstRequest = firstRoute.getRequest();
        final UaiRequest secondRequest = secondRoute.getRequest();

        Integer totalFirstRequest = countTotalItemsOfRequest(firstRequest);
        Integer totalSecondRequest = countTotalItemsOfRequest(secondRequest);

        return totalSecondRequest.compareTo(totalFirstRequest);
    }

    private Integer countTotalItemsOfRequest(final UaiRequest firstRequest) {
        Integer total = 0;

        total += firstRequest.getRequiredHeaderList().size();
        total += firstRequest.getRequiredQueryParamList().size();
        total += firstRequest.getOptionalHeaderList().size();
        total += firstRequest.getOptionalQueryParamList().size();

        return total;
    }
}