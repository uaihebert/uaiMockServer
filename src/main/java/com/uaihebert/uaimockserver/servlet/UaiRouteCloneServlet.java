package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaimockserver.service.UaiRouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UaiRouteCloneServlet extends AbstractServlet {
    @Override
    protected void doPost(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        final String routeId = httpRequest.getParameter("routeId");

        UaiRouteService.clone(routeId);

        send204Response(httpResponse);
    }
}