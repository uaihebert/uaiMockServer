package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiBasicConfigurationDTO;
import com.uaihebert.uaimockserver.context.UaiMockServerContext;

public final class UaiBasicConfigurationDTOFactory {
    private UaiBasicConfigurationDTOFactory() {
    }

    public static UaiBasicConfigurationDTO create() {
        final UaiBasicConfigurationDTO uaiBasicConfigurationDTO = new UaiBasicConfigurationDTO();

        uaiBasicConfigurationDTO.setPort(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getPort());
        uaiBasicConfigurationDTO.setHost(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getHost());
        uaiBasicConfigurationDTO.setContext(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getContext());
        uaiBasicConfigurationDTO.setDefaultContentType(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getDefaultContentTypeResponse());
        uaiBasicConfigurationDTO.setMainUaiFile(UaiFileDTOFactory.create(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getUaiFile()));

        return uaiBasicConfigurationDTO;
    }
}