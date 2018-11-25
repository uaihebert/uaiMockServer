package com.uaihebert.uaimockserver.dto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UaiWebSocketLogDTO {
    private boolean finishedWithError;
    private UaiWebSocketLogRequestDTO logRequest;
    private UaiWebSocketLogCallbackDTO logCallback;
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

    public UaiWebSocketLogCallbackDTO getLogCallback() {
        return logCallback;
    }

    public void setLogResponse(final UaiWebSocketLogResponseDTO logResponse) {
        this.logResponse = logResponse;
    }

    public boolean isFinishedWithError() {
        return finishedWithError;
    }

    public void setFinishedWithError() {
        this.finishedWithError = true;
    }

    public void setLogCallback(final UaiWebSocketLogCallbackDTO logCallback) {
        this.logCallback = logCallback;
    }
}
