package com.uaihebert.uaimockserver.dto.response;

import java.util.List;

public class DummyDataResponseDTO {
    private List<String> generatedPanList;
    private List<String> generatedCvvList;
    private List<String> supportedCreditCardList;
    private List<String> generatedExpirationDateList;

    public List<String> getSupportedCreditCardList() {
        return supportedCreditCardList;
    }

    public void setSupportedCreditCardList(final List<String> supportedCreditCardList) {
        this.supportedCreditCardList = supportedCreditCardList;
    }

    public List<String> getGeneratedPanList() {
        return generatedPanList;
    }

    public void setGeneratedPanList(final List<String> generatedPanList) {
        this.generatedPanList = generatedPanList;
    }

    public List<String> getGeneratedCvvList() {
        return generatedCvvList;
    }

    public void setGeneratedCvvList(final List<String> generatedCvvList) {
        this.generatedCvvList = generatedCvvList;
    }

    public List<String> getGeneratedExpirationDateList() {
        return generatedExpirationDateList;
    }

    public void setGeneratedExpirationDateList(final List<String> generatedExpirationDateList) {
        this.generatedExpirationDateList = generatedExpirationDateList;
    }
}