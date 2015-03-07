package com.uaihebert.uaimockserver.dto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UaiWebSocketLogDTO {
    private UaiWebSocketLogRequestDTO logRequest;
    private UaiWebSocketLogResponseDTO logResponse;
    private final List<String> logList = new ArrayList<String>();

    public UaiWebSocketLogRequestDTO getLogRequest() {
        return logRequest;
    }

    public void setLogRequest(final UaiWebSocketLogRequestDTO logRequest) {
        this.logRequest = logRequest;
    }

    public void addTextLog(final String log) {
        logList.add(log);
    }

    public List<String> getLogList() {
        return Collections.unmodifiableList(logList);
    }

    public UaiWebSocketLogResponseDTO getLogResponse() {
        return logResponse;
    }

    public void setLogResponse(final UaiWebSocketLogResponseDTO logResponse) {
        this.logResponse = logResponse;
    }
}