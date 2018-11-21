package com.uaihebert.uaimockserver.dto.model;

import com.uaihebert.uaimockserver.constants.UaiHttpMethod;

import java.util.ArrayList;
import java.util.List;

public class UaiCallbackDTO {
    private int delayInMilli;

    private String bodyToSend;
    private String completeUrlToCall;

    private UaiHttpMethod httpMethod;

    private final List<UaiHeaderDTO> headerList = new ArrayList<UaiHeaderDTO>();
    private final List<UaiQueryParamDTO> queryParamList = new ArrayList<UaiQueryParamDTO>();

    public int getDelayInMilli() {
        return delayInMilli;
    }

    public void setDelayInMilli(final int delayInMilli) {
        this.delayInMilli = delayInMilli;
    }

    public String getBodyToSend() {
        return bodyToSend;
    }

    public void setBodyToSend(final String bodyToSend) {
        this.bodyToSend = bodyToSend;
    }

    public String getCompleteUrlToCall() {
        return completeUrlToCall;
    }

    public void setCompleteUrlToCall(final String completeUrlToCall) {
        this.completeUrlToCall = completeUrlToCall;
    }

    public UaiHttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(final UaiHttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public List<UaiHeaderDTO> getHeaderList() {
        return headerList;
    }

    public List<UaiQueryParamDTO> getQueryParamList() {
        return queryParamList;
    }
}
