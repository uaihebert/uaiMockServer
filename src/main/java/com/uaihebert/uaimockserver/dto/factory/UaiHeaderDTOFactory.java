package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiHeaderDTO;
import com.uaihebert.uaimockserver.model.UaiHeader;

import java.util.ArrayList;
import java.util.List;

public final class UaiHeaderDTOFactory {
    private UaiHeaderDTOFactory() {
    }

    public static List<UaiHeaderDTO> create(final List<UaiHeader> uaiHeaderList) {
        final List<UaiHeaderDTO> uaiHeaderDTOList = new ArrayList<UaiHeaderDTO>();

        for (UaiHeader uaiHeader : uaiHeaderList) {
            final UaiHeaderDTO uaiHeaderDTO = createUaiHeaderDTO(uaiHeader);
            uaiHeaderDTOList.add(uaiHeaderDTO);
        }
        return uaiHeaderDTOList;
    }

    private static UaiHeaderDTO createUaiHeaderDTO(final UaiHeader uaiHeader) {
        final UaiHeaderDTO uaiHeaderDTO = new UaiHeaderDTO();

        uaiHeaderDTO.setName(uaiHeader.name);
        uaiHeaderDTO.setValueList(uaiHeader.valueList);

        return uaiHeaderDTO;
    }
}