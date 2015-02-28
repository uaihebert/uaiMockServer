package com.uaihebert.uaimockserver.model;

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.constants.RootConstants;

import java.util.ArrayList;
import java.util.List;

public class UaiBasicServerConfiguration {
    private final int port;

    private boolean fileLog;
    private boolean consoleLog;

    private final String host;
    private final String context;
    private final String defaultContentType;

    private final UaiFile mainFile;
    private final List<UaiFile> uaiSecondaryMapFile = new ArrayList<UaiFile>();

    private UaiBasicServerConfiguration(final Config config, final UaiFile mainFile) {
        port = config.getInt(RootConstants.PORT.path);

        fileLog = config.getBoolean(RootConstants.FILE_LOG.path);
        consoleLog = config.getBoolean(RootConstants.CONSOLE_LOG.path);

        host = config.getString(RootConstants.HOST.path);
        context = config.getString(RootConstants.CONTEXT.path);
        defaultContentType = config.getString(RootConstants.DEFAULT_CONTENT_TYPE_RESPONSE.path);
        this.mainFile = mainFile;
    }

    private static UaiBasicServerConfiguration INSTANCE;

    public static void createInstance(final Config config, final UaiFile mainFile) {
        INSTANCE = new UaiBasicServerConfiguration(config, mainFile);
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

    public static UaiFile getMainUaiFile() {
        return INSTANCE.mainFile;
    }

    public static boolean isFileLog() {
        return INSTANCE.fileLog;
    }

    public static boolean isConsoleLog() {
        return INSTANCE.consoleLog;
    }

    public static void addSecondaryFile(final UaiFile uaiFile) {
        INSTANCE.uaiSecondaryMapFile.add(uaiFile);
    }
}
