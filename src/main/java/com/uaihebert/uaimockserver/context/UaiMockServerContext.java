package com.uaihebert.uaimockserver.context;

import com.uaihebert.uaimockserver.factory.UaiMockServerConfigFactory;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.log.backend.LogBuilder;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;

import java.util.ArrayList;
import java.util.List;

public final class UaiMockServerContext {
    private static final String DEFAULT_CONFIGURATION_FILE_NAME = "uaiMockServer.json";

    private static UaiMockServerContext INSTANCE;

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

        UaiRouteRepository.configureRouteData();

        Log.infoFormatted("Configurations of the file [%s] was read with success", fileName);
    }

    public void addRoute(final UaiRoute uaiRoute) {
        // today is only allowed to add a route in the main config file
        // for the future, a new feature is to allow to save in any file
        uaiMockServerConfig.getRouteList().add(uaiRoute);
    }

    public void deleteRoute(final UaiRoute uaiRoute) {
        if (uaiMockServerConfig.getUaiFile().getFullPath().equals(uaiRoute.getUaiFile().getFullPath())) {
            uaiMockServerConfig.getRouteList().remove(uaiRoute);
            return;
        }

        for (UaiMockServerConfig secondaryConfiguration : secondaryMappingList) {
            if (secondaryConfiguration.getUaiFile().getFullPath().equals(uaiRoute.getUaiFile().getFullPath())) {
                secondaryConfiguration.getRouteList().remove(uaiRoute);
                return;
            }
        }
    }

    public static UaiMockServerContext getInstance() {
        return INSTANCE;
    }
}