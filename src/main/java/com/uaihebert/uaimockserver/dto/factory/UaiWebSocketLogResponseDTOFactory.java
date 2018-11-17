package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiLogPairValueDTO;
import com.uaihebert.uaimockserver.dto.model.UaiWebSocketLogResponseDTO;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiResponse;

public final class UaiWebSocketLogResponseDTOFactory {
    private UaiWebSocketLogResponseDTOFactory() {
    }

    public static UaiWebSocketLogResponseDTO create(final UaiResponse uaiResponse) {
        final UaiWebSocketLogResponseDTO logResponseDTO = new UaiWebSocketLogResponseDTO();

        logResponseDTO.setContentType(uaiResponse.getContentType());

        logResponseDTO.setBody(uaiResponse.getBody());

        logResponseDTO.setStatusCode(uaiResponse.getStatusCode());

        for (UaiHeader uaiHeader : uaiResponse.getHeaderList()) {
            final UaiLogPairValueDTO pairValueDTO = new UaiLogPairValueDTO(
                uaiHeader.getName(),
                uaiHeader.getValueList()
            );
            logResponseDTO.getHeaderValueList().add(pairValueDTO);
        }

        return logResponseDTO;
    }
}
