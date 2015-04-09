package com.uaihebert.uaimockserver.dto.response;

import com.uaihebert.uaimockserver.constants.HttpMethod;
import com.uaihebert.uaimockserver.dto.model.UaiRootConfigurationDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.model.BodyValidationType;

import java.util.List;

public class IndexResponseDTO {
    private final HttpMethod[] httpMethodArray = HttpMethod.values();

    private List<UaiRouteDTO> routeList;
    private UaiRootConfigurationDTO rootConfiguration;

    private String defaultProject;
    private List<String> projectList;

    private List<BodyValidationType> bodyValidationTypeList;

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

    public String getDefaultProject() {
        return defaultProject;
    }

    public void setDefaultProject(final String defaultProject) {
        this.defaultProject = defaultProject;
    }

    public List<String> getProjectList() {
        return projectList;
    }

    public void setProjectList(final List<String> projectList) {
        this.projectList = projectList;
    }

    public List<BodyValidationType> getBodyValidationTypeList() {
        return bodyValidationTypeList;
    }

    public void setBodyValidationTypeList(List<BodyValidationType> bodyValidationTypeList) {
        this.bodyValidationTypeList = bodyValidationTypeList;
    }
}