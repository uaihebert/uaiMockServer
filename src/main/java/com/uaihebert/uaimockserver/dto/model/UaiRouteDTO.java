package com.uaihebert.uaimockserver.dto.model;

public class UaiRouteDTO {
    private String id;
    private String project;

    private UaiFileDTO uaiFile;
    private UaiRequestDTO request;
    private UaiResponseDTO response;

    private boolean temporary;

    private int temporaryRepliesTotal;

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

    public String getProject() {
        return project;
    }

    public void setProject(final String project) {
        this.project = project;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public int getTemporaryRepliesTotal() {
        return temporaryRepliesTotal;
    }

    public void setTemporaryRepliesTotal(int temporaryRepliesTotal) {
        this.temporaryRepliesTotal = temporaryRepliesTotal;
    }
}