package com.uaihebert.uaimockserver.dto.model;

import java.util.ArrayList;
import java.util.List;

public class UaiWebSocketLogResponseDTO {
    private int statusCode;

    private String body;
    private String contentType;
    private List<UaiLogPairValueDTO> headerValueList;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
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
}