package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiRootConfigurationDTO;
import com.uaihebert.uaimockserver.context.UaiMockServerContext;

public final class UaiBasicConfigurationDTOFactory {
    private UaiBasicConfigurationDTOFactory() {
    }

    public static UaiRootConfigurationDTO create() {
        final UaiRootConfigurationDTO uaiRootConfigurationDTO = new UaiRootConfigurationDTO();

        uaiRootConfigurationDTO.setPort(UaiMockServerContext.getInstance().uaiMockServerConfig.getPort());
        uaiRootConfigurationDTO.setHost(UaiMockServerContext.getInstance().uaiMockServerConfig.getHost());
        uaiRootConfigurationDTO.setContext(UaiMockServerContext.getInstance().uaiMockServerConfig.getContext());
        uaiRootConfigurationDTO.setDefaultContentType(UaiMockServerContext.getInstance().uaiMockServerConfig.getDefaultContentTypeResponse());
        uaiRootConfigurationDTO.setUaiMainFile(UaiFileDTOFactory.create(UaiMockServerContext.getInstance().uaiMockServerConfig.getUaiFile()));

        return uaiRootConfigurationDTO;
    }
}