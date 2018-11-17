package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiRootConfigurationDTO;
import com.uaihebert.uaimockserver.context.UaiMockServerContext;

public final class UaiBasicConfigurationDTOFactory {
    private UaiBasicConfigurationDTOFactory() {
    }

    public static UaiRootConfigurationDTO create() {
        final UaiRootConfigurationDTO uaiRootConfigurationDTO = new UaiRootConfigurationDTO();

        uaiRootConfigurationDTO.setPort(UaiMockServerContext.getInstance().getUaiMockServerConfig().getPort());
        uaiRootConfigurationDTO.setHost(UaiMockServerContext.getInstance().getUaiMockServerConfig().getHost());
        uaiRootConfigurationDTO.setContext(UaiMockServerContext.getInstance().getUaiMockServerConfig().getContext());
        uaiRootConfigurationDTO.setDefaultContentType(
            UaiMockServerContext.getInstance().getUaiMockServerConfig().getDefaultContentTypeResponse()
        );
        uaiRootConfigurationDTO.setUaiMainFile(UaiFileDTOFactory.create(
            UaiMockServerContext.getInstance().getUaiMockServerConfig().getUaiFile())
        );

        return uaiRootConfigurationDTO;
    }
}
