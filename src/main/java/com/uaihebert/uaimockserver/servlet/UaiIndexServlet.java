package com.uaihebert.uaimockserver.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class UaiIndexServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("text/html");

        final OutputStream writer = httpResponse.getOutputStream();

        printResource(writer, "/pages/index.html");

        writer.flush();
    }

    protected void printResource(final OutputStream writer, final String resourcePath) throws IOException {
        final InputStreamReader streamReader = new InputStreamReader(getClass().getResourceAsStream(resourcePath));
        final BufferedReader bufferedReader = new BufferedReader(streamReader);

        try {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                writer.write(currentLine.getBytes());
            }
        } finally {
            streamReader.close();
            bufferedReader.close();
        }
    }
}