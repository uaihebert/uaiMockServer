package com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.factory.UaiMockServerConfigFactory;
import com.uaihebert.uaimockserver.factory.UaiRouteMapperFactory;
import com.uaihebert.uaimockserver.log.Log;
import com.uaihebert.uaimockserver.log.LogBuilder;

import java.util.ArrayList;
import java.util.List;

public final class UaiMockServerContext {
    private static final String DEFAULT_CONFIGURATION_FILE_NAME = "uaiMockServer.json";

    public static UaiMockServerContext INSTANCE;

    public final UaiMockServerConfig uaiMockServerConfig;

    public final List<UaiMockServerConfig> secondaryMappingList = new ArrayList<UaiMockServerConfig>();

    private UaiMockServerContext(final UaiMockServerConfig uaiMockServerConfig) {
        this.uaiMockServerConfig = uaiMockServerConfig;
    }

    public static void createInstance() {
        createInstance(DEFAULT_CONFIGURATION_FILE_NAME);
    }

    public static void createInstance(final String fileName) {
        final UaiMockServerConfig uaiMockServerConfig = UaiMockServerConfigFactory.create(fileName);
        INSTANCE = new UaiMockServerContext(uaiMockServerConfig);

        final List<UaiMockServerConfig> secondaryMappingList = UaiMockServerConfigFactory.create(INSTANCE.uaiMockServerConfig.getMappingRoutesFileList());
        INSTANCE.secondaryMappingList.addAll(secondaryMappingList);

        LogBuilder.createInstance();

        UaiRouteMapperFactory.configureRouteMapper();

        Log.infoFormatted("Configurations of the file [%s] was read with success", fileName);
    }
}