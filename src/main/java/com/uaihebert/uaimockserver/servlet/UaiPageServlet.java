package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaimockserver.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UaiPageServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("text/html");

        super.addDefaultHeaders(httpResponse);

        final String fileName = httpRequest.getParameter("fileName");

        if (StringUtils.isBlank(fileName)) {
            printResource(httpResponse.getOutputStream(), "/pages/index/index.html");
            return;
        }

        printResource(httpResponse.getOutputStream(), "/pages/" + fileName +".html");
    }
}