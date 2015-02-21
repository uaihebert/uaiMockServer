package com.uaihebert.uaimockserver.model;

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.constants.RootConstants;

public class UaiBasicServerConfiguration {
    public final int port;

    public final String host;
    public final String context;
    public final String defaultContentType;

    public UaiBasicServerConfiguration(final Config config) {
        port = config.getInt(RootConstants.PORT.path);

        host = config.getString(RootConstants.HOST.path);
        context = config.getString(RootConstants.CONTEXT.path);
        defaultContentType = config.getString(RootConstants.DEFAULT_CONTENT_TYPE_RESPONSE.path);
    }
}
