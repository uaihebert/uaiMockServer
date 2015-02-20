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
        uaiRequestDTO.setHoldRequestInMilli(uaiRequest.holdRequestInMilli);
        uaiRequestDTO.setMethod(uaiRequest.method);
        uaiRequestDTO.setPath(uaiRequest.path);
        uaiRequestDTO.setRequiredContentType(uaiRequest.requiredContentType);

        final List<UaiHeaderDTO> uaiHeaderDTOList = UaiHeaderDTOFactory.create(uaiRequest.requiredHeaderList);
        uaiRequestDTO.setRequiredHeaderList(uaiHeaderDTOList);

        final List<UaiQueryParamDTO> uaiQueryParamDTOList = UaiQueryParamDTOFactory.create(uaiRequest.requiredQueryParamList);
        uaiRequestDTO.setRequiredQueryParamList(uaiQueryParamDTOList);

        return uaiRequestDTO;
    }
}