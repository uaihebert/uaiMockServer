package com.uaihebert.uaimockserver.dto.model;

import java.util.List;

public class UaiQueryParamDTO {
    private String name;
    private List<String> valueList;

    public UaiQueryParamDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(final List<String> valueList) {
        this.valueList = valueList;
    }
}
