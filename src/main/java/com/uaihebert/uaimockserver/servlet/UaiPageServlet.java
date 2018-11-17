package com.uaihebert.uaimockserver.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UaiPageServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        super.addDefaultHeaders(response);

        final String fileName = request.getParameter("fileName");

        printResource(response.getOutputStream(), "/pages/" + fileName.replace("_", "/") + ".html");
    }
}
