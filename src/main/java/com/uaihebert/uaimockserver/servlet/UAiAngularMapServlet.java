package com.uaihebert.uaimockserver.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UAiAngularMapServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        super.addDefaultHeaders(response);

        printResource(response.getOutputStream(), "/javascript/angular.min.js.map");
    }
}
