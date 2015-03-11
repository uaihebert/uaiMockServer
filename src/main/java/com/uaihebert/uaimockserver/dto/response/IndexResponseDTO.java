package com.uaihebert.uaimockserver.dto.response;

import com.uaihebert.uaimockserver.constants.HttpMethod;
import com.uaihebert.uaimockserver.dto.model.UaiRootConfigurationDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;

import java.util.List;

public class IndexResponseDTO {
    private final HttpMethod[] httpMethodArray = HttpMethod.values();

    private List<UaiRouteDTO> routeList;
    private UaiRootConfigurationDTO rootConfiguration;

    public HttpMethod[] getHttpMethodArray() {
        return httpMethodArray;
    }

    public List<UaiRouteDTO> getRouteList() {
        return routeList;
    }

    public void setRouteList(final List<UaiRouteDTO> routeList) {
        this.routeList = routeList;
    }

    public UaiRootConfigurationDTO getRootConfiguration() {
        return rootConfiguration;
    }

    public void setRootConfiguration(final UaiRootConfigurationDTO rootConfiguration) {
        this.rootConfiguration = rootConfiguration;
    }
}