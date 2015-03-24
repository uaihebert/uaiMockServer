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
        uaiRequestDTO.setHoldRequestInMilli(uaiRequest.holdTheRequestInMilli);
        uaiRequestDTO.setMethod(uaiRequest.method);
        uaiRequestDTO.setName(uaiRequest.name);
        uaiRequestDTO.setDescription(uaiRequest.description);
        uaiRequestDTO.setPath(uaiRequest.path);
        uaiRequestDTO.setRequiredContentType(uaiRequest.requiredContentType);

        final List<UaiHeaderDTO> uaiHeaderDTOList = UaiHeaderDTOFactory.create(uaiRequest.getRequiredHeaderList());
        uaiRequestDTO.setRequiredHeaderList(uaiHeaderDTOList);

        final List<UaiQueryParamDTO> uaiQueryParamDTOList = UaiQueryParamDTOFactory.create(uaiRequest.getRequiredQueryParamList());
        uaiRequestDTO.setRequiredQueryParamList(uaiQueryParamDTOList);

        return uaiRequestDTO;
    }
}