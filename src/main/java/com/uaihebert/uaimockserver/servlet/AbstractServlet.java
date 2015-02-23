package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaimockserver.configuration.ProjectConfiguration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

class AbstractServlet extends HttpServlet {
    void printResource(final OutputStream writer, final String resourcePath) throws IOException {
        final InputStreamReader streamReader = new InputStreamReader(AbstractServlet.class.getResourceAsStream(resourcePath), ProjectConfiguration.ENCODING.value);
        final BufferedReader bufferedReader = new BufferedReader(streamReader);

        try {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                currentLine = "\n" + currentLine;
                writer.write(currentLine.getBytes(ProjectConfiguration.ENCODING.value));
            }

            writer.flush();
        } finally {
            writer.close();
            streamReader.close();
            bufferedReader.close();
        }
    }

    public void addDefaultHeaders(final HttpServletResponse httpResponse) {
        httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
    }
}