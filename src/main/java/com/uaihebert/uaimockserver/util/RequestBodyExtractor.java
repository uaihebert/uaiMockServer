package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.configuration.ProjectConfiguration;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public final class RequestBodyExtractor {
    private RequestBodyExtractor() {

    }

    public static <T> T extract(final HttpServletRequest httpServletRequest, Class<T> classToReturn) throws IOException {
        final String body = IOUtils.toString(httpServletRequest.getInputStream(), ProjectConfiguration.ENCODING.value);

        return JsonUtil.fromJson(body, classToReturn);
    }
}