package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiBasicConfigurationDTO;
import com.uaihebert.uaimockserver.model.UaiBasicServerConfiguration;

public final class UaiBasicConfigurationDTOFactory {
    private UaiBasicConfigurationDTOFactory() {
    }

    public static UaiBasicConfigurationDTO create() {
        final UaiBasicConfigurationDTO uaiBasicConfigurationDTO = new UaiBasicConfigurationDTO();

        uaiBasicConfigurationDTO.setPort(UaiBasicServerConfiguration.getPort());
        uaiBasicConfigurationDTO.setHost(UaiBasicServerConfiguration.getHost());
        uaiBasicConfigurationDTO.setContext(UaiBasicServerConfiguration.getContext());
        uaiBasicConfigurationDTO.setDefaultContentType(UaiBasicServerConfiguration.getDefaultContentType());
        uaiBasicConfigurationDTO.setMainUaiFile(UaiFileDTOFactory.create(UaiBasicServerConfiguration.getMainUaiFile()));

        return uaiBasicConfigurationDTO;
    }
}