package com.uaihebert.uaimockserver.service;

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.dto.model.UaiRootConfigurationDTO;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.util.FileUtil;

public final class UaiRootContextService {
    private UaiRootContextService() {
    }

    public static void update(final UaiRootConfigurationDTO uaiRouteConfigDTO) {
        final UaiMockServerConfig mainConfig = UaiMockServerContext.getInstance().uaiMockServerConfig;
        mainConfig.setContext(uaiRouteConfigDTO.getContext());
        mainConfig.setPort(uaiRouteConfigDTO.getPort());
        mainConfig.setHost(uaiRouteConfigDTO.getHost());
        mainConfig.setConsoleLog(uaiRouteConfigDTO.getConsoleLog());
        mainConfig.setFileLog(uaiRouteConfigDTO.getFileLog());
        mainConfig.setDefaultContentTypeResponse(uaiRouteConfigDTO.getDefaultContentType());

        flushData();
    }

    private static void flushData() {
        FileUtil.writeUpdatesToFile();
    }
}
