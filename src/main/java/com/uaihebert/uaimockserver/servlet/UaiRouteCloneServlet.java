package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaimockserver.service.UaiRouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UaiRouteCloneServlet extends AbstractServlet {
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String routeId = request.getParameter("routeId");

        UaiRouteService.clone(routeId);

        send204Response(response);
    }
}
