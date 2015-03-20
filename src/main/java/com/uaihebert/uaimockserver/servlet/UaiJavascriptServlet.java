package com.uaihebert.uaimockserver.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UaiJavascriptServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("application/javascript");

        super.addDefaultHeaders(httpResponse);

        final String fileName = httpRequest.getParameter("fileName");

        printResource(httpResponse.getOutputStream(), "/javascript/" + fileName + ".js");
    }
}