package com.uaihebert.uaimockserver.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CssServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("text/css");

        final String fileName = httpRequest.getParameter("fileName");

        printResource(httpResponse.getOutputStream(), "/css/" + fileName +".css");
    }
}