package com.uaihebert.uaimockserver.dto.model;

import com.uaihebert.uaimockserver.model.BodyValidationType;

import java.util.List;

public class UaiRequestDTO {
    private String name;
    private String path;
    private String body;
    private String method;
    private String description;
    private String requiredContentType;

    private BodyValidationType bodyValidationType;

    private Long holdRequestInMilli;

    private Boolean isBodyRequired;

    private List<UaiHeaderDTO> optionalHeaderList;
    private List<UaiQueryParamDTO> optionalQueryParamList;

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

    public List<UaiHeaderDTO> getOptionalHeaderList() {
        return optionalHeaderList;
    }

    public void setOptionalHeaderList(final List<UaiHeaderDTO> optionalHeaderList) {
        this.optionalHeaderList = optionalHeaderList;
    }

    public List<UaiQueryParamDTO> getOptionalQueryParamList() {
        return optionalQueryParamList;
    }

    public void setOptionalQueryParamList(final List<UaiQueryParamDTO> optionalQueryParamList) {
        this.optionalQueryParamList = optionalQueryParamList;
    }

    public BodyValidationType getBodyValidationType() {
        return bodyValidationType;
    }

    public void setBodyValidationType(BodyValidationType bodyValidationType) {
        this.bodyValidationType = bodyValidationType;
    }

    public void setIsBodyRequired(Boolean isBodyRequired) {
        this.isBodyRequired = isBodyRequired;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }
}