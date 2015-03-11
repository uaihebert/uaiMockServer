package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiRootConfigurationDTO;
import com.uaihebert.uaimockserver.context.UaiMockServerContext;

public final class UaiBasicConfigurationDTOFactory {
    private UaiBasicConfigurationDTOFactory() {
    }

    public static UaiRootConfigurationDTO create() {
        final UaiRootConfigurationDTO uaiRootConfigurationDTO = new UaiRootConfigurationDTO();

        uaiRootConfigurationDTO.setPort(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getPort());
        uaiRootConfigurationDTO.setHost(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getHost());
        uaiRootConfigurationDTO.setContext(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getContext());
        uaiRootConfigurationDTO.setDefaultContentType(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getDefaultContentTypeResponse());
        uaiRootConfigurationDTO.setUaiMainFile(UaiFileDTOFactory.create(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getUaiFile()));

        return uaiRootConfigurationDTO;
    }
}