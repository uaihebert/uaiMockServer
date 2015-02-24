package com.uaihebert.uaimockserver.dto.model;

public class UaiRouteDTO {
    private String id;
    private UaiFileDTO uaiFile;
    private UaiRequestDTO request;
    private UaiResponseDTO response;

    public UaiRouteDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
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