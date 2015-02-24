package com.uaihebert.uaimockserver.dto.model;

public class UaiRouteDTO {
    private UaiFileDTO uaiFile;
    private UaiRequestDTO request;
    private UaiResponseDTO response;

    public UaiRouteDTO() {
    }

    public UaiFileDTO getUaiFile() {
        return uaiFile;
    }

    public void setUaiFile(final UaiFileDTO uaiFile) {
        this.uaiFile = uaiFile;
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