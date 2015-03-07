package com.uaihebert.uaimockserver.dto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebSocketLogDTO {
    private UaiLogRequestDTO logRequest;
    private final List<String> logList = new ArrayList<String>();

    public UaiLogRequestDTO getLogRequest() {
        return logRequest;
    }

    public void setLogRequest(final UaiLogRequestDTO logRequest) {
        this.logRequest = logRequest;
    }

    public void addTextLog(final String log) {
        logList.add(log);
    }

    public List<String> getLogList() {
        return Collections.unmodifiableList(logList);
    }
}