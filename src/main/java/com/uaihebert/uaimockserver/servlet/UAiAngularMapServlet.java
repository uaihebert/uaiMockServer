package com.uaihebert.uaimockserver.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UAiAngularMapServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("text/plain");

        super.addDefaultHeaders(httpResponse);

        printResource(httpResponse.getOutputStream(), "/javascript/angular.min.js.map");
    }
}