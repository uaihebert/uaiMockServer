package com.uaihebert.uaimockserver.model;

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.constants.RootConstants;

public class UaiBasicServerConfiguration {
    private final int port;

    private final String host;
    private final String context;
    private final String defaultContentType;

    private UaiBasicServerConfiguration(final Config config) {
        port = config.getInt(RootConstants.PORT.path);

        host = config.getString(RootConstants.HOST.path);
        context = config.getString(RootConstants.CONTEXT.path);
        defaultContentType = config.getString(RootConstants.DEFAULT_CONTENT_TYPE_RESPONSE.path);
    }

    private static UaiBasicServerConfiguration INSTANCE;

    public static void createInstance(final Config config) {
        INSTANCE = new UaiBasicServerConfiguration(config);
    }

    public static String getDefaultContentType() {
        return INSTANCE.defaultContentType;
    }

    public static String getContext() {
        return INSTANCE.context;
    }

    public static String getHost() {
        return INSTANCE.host;
    }

    public static int getPort() {
        return INSTANCE.port;
    }
}
