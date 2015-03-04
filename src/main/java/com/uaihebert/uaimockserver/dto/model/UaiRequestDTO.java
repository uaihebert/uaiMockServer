package com.uaihebert.uaimockserver.dto.model;

import java.util.List;

public class UaiRequestDTO {
    private String name;
    private String path;
    private String method;
    private String description;
    private String requiredContentType;

    private Long holdRequestInMilli;

    private Boolean isBodyRequired;

    private List<UaiHeaderDTO> requiredHeaderList;
    private List<UaiQueryParamDTO> requiredQueryParamList;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getRequiredContentType() {
        return requiredContentType;
    }

    public void setRequiredContentType(final String requiredContentType) {
        this.requiredContentType = requiredContentType;
    }

    public Long getHoldRequestInMilli() {
        return holdRequestInMilli;
    }

    public void setHoldRequestInMilli(final Long holdRequestInMilli) {
        this.holdRequestInMilli = holdRequestInMilli;
    }

    public Boolean isBodyRequired() {
        return isBodyRequired;
    }

    public void setBodyRequired(final Boolean isBodyRequired) {
        this.isBodyRequired = isBodyRequired;
    }

    public List<UaiHeaderDTO> getRequiredHeaderList() {
        return requiredHeaderList;
    }

    public void setRequiredHeaderList(final List<UaiHeaderDTO> requiredHeaderList) {
        this.requiredHeaderList = requiredHeaderList;
    }

    public List<UaiQueryParamDTO> getRequiredQueryParamList() {
        return requiredQueryParamList;
    }

    public void setRequiredQueryParamList(final List<UaiQueryParamDTO> requiredQueryParamList) {
        this.requiredQueryParamList = requiredQueryParamList;
    }
}