package com.uaihebert.uaimockserver.servlet;


import com.uaihebert.uaimockserver.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JavascriptServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("application/javascript");

        super.addDefaultHeaders(httpResponse);

        final String fileName = httpRequest.getParameter("fileName");

        if (StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException("The Javascript servlet was invoked, but not file name was given. \n " +
                    "Please, provide a file name using the query param like ---> ?fileName=uaiJavaScriptFile \n");
        }

        printResource(httpResponse.getOutputStream(), "/javascript/" + fileName + ".js");
    }
}
// todo add in the UaiWebSocketLog the IP of who did the request
// todo adjust active header menu in the GUI
// todo create RootConfiguration page in the GUI