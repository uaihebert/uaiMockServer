package com.uaihebert.uaimockserver.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestBodyExtractor {
    private RequestBodyExtractor() {

    }

    public static <T> T extract(final HttpServletRequest httpServletRequest, Class<T> classToReturn) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            final InputStream inputStream = httpServletRequest.getInputStream();

            if (inputStream == null) {
                return null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        final String body = stringBuilder.toString();

        return new Gson().fromJson(body, classToReturn);
    }
}
