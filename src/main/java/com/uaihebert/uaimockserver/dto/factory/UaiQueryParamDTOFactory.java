package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiQueryParamDTO;
import com.uaihebert.uaimockserver.model.UaiQueryParam;

import java.util.ArrayList;
import java.util.List;

public final class UaiQueryParamDTOFactory {
    private UaiQueryParamDTOFactory() {
    }

    public static List<UaiQueryParamDTO> create(final List<UaiQueryParam> uaiQueryParamList) {
        final List<UaiQueryParamDTO> uaiQueryParamDTOList = new ArrayList<UaiQueryParamDTO>();

        for (UaiQueryParam uaiQueryParam : uaiQueryParamList) {
            final UaiQueryParamDTO uaiQueryParamDTO = createUaiQueryParamDTO(uaiQueryParam);
            uaiQueryParamDTOList.add(uaiQueryParamDTO);
        }
        return uaiQueryParamDTOList;
    }

    private static UaiQueryParamDTO createUaiQueryParamDTO(final UaiQueryParam uaiQueryParam) {
        final UaiQueryParamDTO uaiQueryParamDTO = new UaiQueryParamDTO();

        uaiQueryParamDTO.setName(uaiQueryParam.getName());
        uaiQueryParamDTO.setValueList(uaiQueryParam.getValueList());

        return uaiQueryParamDTO;
    }
}