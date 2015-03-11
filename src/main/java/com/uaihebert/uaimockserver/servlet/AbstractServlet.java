package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaimockserver.configuration.ProjectConfiguration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

class AbstractServlet extends HttpServlet {
    void writeInResponse(final HttpServletResponse httpResponse, final String text) throws IOException {
        final OutputStream outputStream = httpResponse.getOutputStream();

        try {
            outputStream.write(text.getBytes(ProjectConfiguration.ENCODING.value));

            outputStream.flush();
        } finally {
            outputStream.close();
        }
    }

    void send204Response(final HttpServletResponse httpResponse) throws IOException {
        httpResponse.setContentType("application/json");
        httpResponse.setStatus(204);

        final OutputStream outputStream = httpResponse.getOutputStream();
        outputStream.close();
    }

    void addDefaultHeaders(final HttpServletResponse httpResponse) {
        httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
    }
}