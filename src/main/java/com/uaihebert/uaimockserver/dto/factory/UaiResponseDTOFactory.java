package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiHeaderDTO;
import com.uaihebert.uaimockserver.dto.model.UaiResponseDTO;
import com.uaihebert.uaimockserver.model.UaiResponse;

import java.util.List;

public class UaiResponseDTOFactory {
    private UaiResponseDTOFactory() {
    }

    public static UaiResponseDTO create(final UaiResponse uaiResponse) {
        final UaiResponseDTO uaiResponseDTO = new UaiResponseDTO();
        uaiResponseDTO.setStatusCode(uaiResponse.getStatusCode());
        uaiResponseDTO.setBody(uaiResponse.getBody());
        uaiResponseDTO.setContentType(uaiResponse.getContentType());
        uaiResponseDTO.setBodyPointingToFile(uaiResponse.isBodyPointingToFile());

        final List<UaiHeaderDTO> uaiHeaderDTOList = UaiHeaderDTOFactory.create(uaiResponse.getHeaderList());
        uaiResponseDTO.setHeaderList(uaiHeaderDTOList);

        return uaiResponseDTO;
    }
}