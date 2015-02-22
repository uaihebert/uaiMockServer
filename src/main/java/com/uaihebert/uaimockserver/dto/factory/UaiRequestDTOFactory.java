package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiHeaderDTO;
import com.uaihebert.uaimockserver.dto.model.UaiQueryParamDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRequestDTO;
import com.uaihebert.uaimockserver.model.UaiRequest;

import java.util.List;

public final class UaiRequestDTOFactory {
    private UaiRequestDTOFactory() {
    }

    public static UaiRequestDTO create(final UaiRequest uaiRequest) {
        final UaiRequestDTO uaiRequestDTO = new UaiRequestDTO();
        uaiRequestDTO.setBodyRequired(uaiRequest.isBodyRequired);
        setHoldRequestInMilli(uaiRequest, uaiRequestDTO);
        uaiRequestDTO.setMethod(uaiRequest.method);
        uaiRequestDTO.setName(uaiRequest.name);
        uaiRequestDTO.setDescription(uaiRequest.description);
        uaiRequestDTO.setPath(uaiRequest.path);
        uaiRequestDTO.setRequiredContentType(uaiRequest.requiredContentType);

        final List<UaiHeaderDTO> uaiHeaderDTOList = UaiHeaderDTOFactory.create(uaiRequest.requiredHeaderList);
        uaiRequestDTO.setRequiredHeaderList(uaiHeaderDTOList);

        final List<UaiQueryParamDTO> uaiQueryParamDTOList = UaiQueryParamDTOFactory.create(uaiRequest.requiredQueryParamList);
        uaiRequestDTO.setRequiredQueryParamList(uaiQueryParamDTOList);

        return uaiRequestDTO;
    }

    private static void setHoldRequestInMilli(final UaiRequest uaiRequest, final UaiRequestDTO uaiRequestDTO) {
        if (uaiRequest.holdRequestInMilli != 0) {
            uaiRequestDTO.setHoldRequestInMilli(uaiRequest.holdRequestInMilli);
            return;
        }

        uaiRequestDTO.setHoldRequestInMilli(null);
    }
}