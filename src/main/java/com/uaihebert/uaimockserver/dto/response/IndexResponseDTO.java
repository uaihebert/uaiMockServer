package com.uaihebert.uaimockserver.dto.response;

import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;

import java.util.List;

public class IndexResponseDTO {
    private List<UaiRouteDTO> routeList;

    public List<UaiRouteDTO> getRouteList() {
        return routeList;
    }

    public void setRouteList(final List<UaiRouteDTO> routeList) {
        this.routeList = routeList;
    }
}