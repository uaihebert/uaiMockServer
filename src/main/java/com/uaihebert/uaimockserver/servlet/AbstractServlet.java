package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaimockserver.configuration.ProjectConfiguration;
import com.uaihebert.uaimockserver.model.HttpStatusCode;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


abstract class AbstractServlet extends HttpServlet {
    void writeInResponse(final HttpServletResponse httpResponse, final String text) throws IOException {
        httpResponse.setContentType("application/json");

        final OutputStream outputStream = httpResponse.getOutputStream();

        try {
            outputStream.write(text.getBytes(ProjectConfiguration.ENCODING.value));
        } finally {
            outputStream.close();
        }
    }

    void printResource(final OutputStream writer, final String resourcePath) throws IOException {
        final InputStream stream = AbstractServlet.class.getResourceAsStream(resourcePath);
        final InputStreamReader streamReader = new InputStreamReader(stream, ProjectConfiguration.ENCODING.value);
        final BufferedReader bufferedReader = new BufferedReader(streamReader);

        try {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                currentLine = "\n" + currentLine;
                writer.write(currentLine.getBytes(ProjectConfiguration.ENCODING.value));
            }

        } finally {
            writer.close();
            streamReader.close();
            bufferedReader.close();
        }
    }

    void send204Response(final HttpServletResponse httpResponse) throws IOException {
        httpResponse.setContentType("application/json");
        httpResponse.setStatus(HttpStatusCode.NO_CONTENT.code);

        // have to send it or it will give an error in travis-ci
        writeInResponse(httpResponse, "{empty:body}");
    }

    void addDefaultHeaders(final HttpServletResponse httpResponse) {
        httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
    }
}
