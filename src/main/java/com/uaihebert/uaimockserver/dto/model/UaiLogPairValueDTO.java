package com.uaihebert.uaimockserver.dto.model;

import java.util.List;

public class UaiLogPairValueDTO {
    private String key;
    private List<String> valueList;

    public UaiLogPairValueDTO() {
    }

    public UaiLogPairValueDTO(final String key, final List<String> valueList) {
        this.key = key;
        this.valueList = valueList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(final List<String> valueList) {
        this.valueList = valueList;
    }
}
