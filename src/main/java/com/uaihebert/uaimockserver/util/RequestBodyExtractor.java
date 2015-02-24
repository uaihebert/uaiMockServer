package com.uaihebert.uaimockserver.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class RequestBodyExtractor {
    private RequestBodyExtractor() {

    }

    public static <T> T extract(final HttpServletRequest httpServletRequest, Class<T> classToReturn) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();

        final BufferedReader bufferedReader = populateStringBuilder(httpServletRequest, stringBuilder);
        if (bufferedReader != null) {
            bufferedReader.close();
        }

        final String body = stringBuilder.toString();

        return new Gson().fromJson(body, classToReturn);
    }

    private static BufferedReader populateStringBuilder(final HttpServletRequest httpServletRequest, final StringBuilder stringBuilder) throws IOException {
        final InputStream inputStream = httpServletRequest.getInputStream();

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        char[] charBuffer = new char[128];
        int bytesRead;
        while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
            stringBuilder.append(charBuffer, 0, bytesRead);
        }

        return bufferedReader;
    }
}
