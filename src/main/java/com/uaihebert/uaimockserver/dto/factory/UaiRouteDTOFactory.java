package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiFileDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRequestDTO;
import com.uaihebert.uaimockserver.dto.model.UaiResponseDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.model.UaiRoute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class UaiRouteDTOFactory {
    private UaiRouteDTOFactory() {
    }

    public static List<UaiRouteDTO> create(final List<UaiRoute> uaiRouteList) {
        if (uaiRouteList == null) {
            return Collections.emptyList();
        }

        final List<UaiRouteDTO> uaiRouteDTOList = new ArrayList<UaiRouteDTO>();

        for (UaiRoute uaiRoute : uaiRouteList) {
            final UaiRouteDTO uaiRouteDTO = createUaiRouteDTO(uaiRoute);
            uaiRouteDTOList.add(uaiRouteDTO);
        }

        return uaiRouteDTOList;
    }

    private static UaiRouteDTO createUaiRouteDTO(final UaiRoute uaiRoute) {
        final UaiFileDTO uaiFileDTO = UaiFileDTOFactory.create(uaiRoute.getUaiFile());
        final UaiRequestDTO uaiRequestDTO = UaiRequestDTOFactory.create(uaiRoute.getRequest());
        final UaiResponseDTO uaiResponseDTO = UaiResponseDTOFactory.create(uaiRoute.getResponse());

        final UaiRouteDTO uaiRouteDTO = new UaiRouteDTO();
        uaiRouteDTO.setId(uaiRoute.getId());
        uaiRouteDTO.setProject(uaiRoute.getProject());
        uaiRouteDTO.setUaiFile(uaiFileDTO);
        uaiRouteDTO.setRequest(uaiRequestDTO);
        uaiRouteDTO.setResponse(uaiResponseDTO);
        uaiRouteDTO.setTemporary(uaiRoute.isTemporary());
        uaiRouteDTO.setTemporaryRepliesTotal(uaiRoute.getTemporaryRepliesTotal());

        return uaiRouteDTO;
    }
}