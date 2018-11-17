package com.uaihebert.uaimockserver.dto.model;

import java.util.ArrayList;
import java.util.List;

public class UaiWebSocketLogRequestDTO {

    private String path;
    private String method;
    private String arrivedAt;
    private String contentType;
    private String whoInvokedAddress;

    private List<UaiLogPairValueDTO> headerValueList;
    private List<UaiLogPairValueDTO> queryParamValueList;

    public UaiWebSocketLogRequestDTO() {
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

    public String getWhoInvokedAddress() {
        return whoInvokedAddress;
    }

    public void setWhoInvokedAddress(final String whoInvokedAddress) {
        this.whoInvokedAddress = whoInvokedAddress;
    }

    public String getArrivedAt() {
        return arrivedAt;
    }

    public void setArrivedAt(final String arrivedAt) {
        this.arrivedAt = arrivedAt;
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
