package com.uaihebert.uaimockserver.dto.model;

public class UaiRouteDTO {
    private UaiRequestDTO request;
    private UaiResponseDTO response;

    public UaiRouteDTO() {
    }

    public UaiRequestDTO getRequest() {
        return request;
    }

    public void setRequest(final UaiRequestDTO request) {
        this.request = request;
    }

    public UaiResponseDTO getResponse() {
        return response;
    }

    public void setResponse(final UaiResponseDTO response) {
        this.response = response;
    }
}