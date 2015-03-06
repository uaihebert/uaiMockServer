package com.uaihebert.uaimockserver.dto.model;

import java.util.ArrayList;
import java.util.List;

public class UaiLogRequestDTO {
    private String path;
    private String method;
    private String contentType;

    private List<UaiLogPairValueDTO> headerValueList;
    private List<UaiLogPairValueDTO> queryParamValueList;

    public UaiLogRequestDTO() {
    }

    public UaiLogRequestDTO(final String path, final String method, final String contentType, final List<UaiLogPairValueDTO> headerValueList, final List<UaiLogPairValueDTO> queryParamValueList) {
        this.path = path;
        this.method = method;
        this.contentType = contentType;
        this.headerValueList = headerValueList;
        this.queryParamValueList = queryParamValueList;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public List<UaiLogPairValueDTO> getHeaderValueList() {
        if (headerValueList == null) {
            headerValueList = new ArrayList<UaiLogPairValueDTO>();
        }

        return headerValueList;
    }

    public void setHeaderValueList(final List<UaiLogPairValueDTO> headerValueList) {
        this.headerValueList = headerValueList;
    }

    public List<UaiLogPairValueDTO> getQueryParamValueList() {
        if (queryParamValueList == null) {
            queryParamValueList = new ArrayList<UaiLogPairValueDTO>();
        }

        return queryParamValueList;
    }

    public void setQueryParamValueList(final List<UaiLogPairValueDTO> queryParamValueList) {
        this.queryParamValueList = queryParamValueList;
    }
}