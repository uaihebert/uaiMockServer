package com.uaihebert.uaimockserver.servlet;

import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class AbstractServlet extends HttpServlet {

    protected void printResource(final OutputStream writer, final String resourcePath) throws IOException {
        final InputStreamReader streamReader = new InputStreamReader(getClass().getResourceAsStream(resourcePath));
        final BufferedReader bufferedReader = new BufferedReader(streamReader);

        try {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                writer.write(currentLine.getBytes());
            }

            writer.flush();
        } finally {
            writer.close();
            streamReader.close();
            bufferedReader.close();
        }
    }
}
