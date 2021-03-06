package com.uaihebert.uaimockserver.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UaiJavascriptServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/javascript");

        super.addDefaultHeaders(response);

        final String fileName = request.getParameter("fileName");

        printResource(response.getOutputStream(), "/javascript/" + fileName + ".js");
    }
}
