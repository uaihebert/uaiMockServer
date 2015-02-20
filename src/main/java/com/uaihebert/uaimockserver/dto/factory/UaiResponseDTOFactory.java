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
        uaiResponseDTO.setStatusCode(uaiResponse.statusCode);
        uaiResponseDTO.setBody(uaiResponse.body);
        uaiResponseDTO.setContentType(uaiResponse.contentType);

        final List<UaiHeaderDTO> uaiHeaderDTOList = UaiHeaderDTOFactory.create(uaiResponse.headerList);
        uaiResponseDTO.setHeaderList(uaiHeaderDTOList);

        return uaiResponseDTO;
    }
}
// todo remove StringUtil from apache and create one in here. The size is to big for us to use only one method