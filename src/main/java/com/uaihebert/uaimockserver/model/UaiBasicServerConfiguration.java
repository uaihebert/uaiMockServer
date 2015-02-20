package com.uaihebert.uaimockserver.model;

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.constants.RootConstants;
import com.uaihebert.uaimockserver.log.LogBuilder;

public class UaiBasicServerConfiguration {
    public final int port;

    public final String host;
    public final String context;
    public final String defaultContentType;

    // todo delete these configurations
    public final boolean fileLog;
    public final boolean consoleLog;

    public UaiBasicServerConfiguration(final Config config) {
        fileLog = config.getBoolean(RootConstants.FILE_LOG.path);
        consoleLog = config.getBoolean(RootConstants.CONSOLE_LOG.path);

        LogBuilder.createInstance(this);

        port = config.getInt(RootConstants.PORT.path);

        host = config.getString(RootConstants.HOST.path);
        context = config.getString(RootConstants.CONTEXT.path);
        defaultContentType = config.getString(RootConstants.DEFAULT_CONTENT_TYPE_RESPONSE.path);
    }
}
