package com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.dto.model.UaiCallbackDTO;
import com.uaihebert.uaimockserver.model.UaiCallback;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;

import java.util.List;

public final class UaiCallbackFactory {
    private UaiCallbackFactory() {
    }

    public static UaiCallback create(final UaiCallbackDTO callbackDTO) {
        if (callbackDTO == null) {
            return null;
        }

        final UaiCallback callback = new UaiCallback();

        callback.setDelayInMilli(callbackDTO.getDelayInMilli());
        callback.setHttpMethod(callbackDTO.getHttpMethod());
        callback.setBodyToSend(callbackDTO.getBodyToSend());
        callback.setCompleteUrlToCall(callbackDTO.getCompleteUrlToCall());

        final List<UaiHeader> headerList = UaiHeaderFactory.create(callbackDTO.getHeaderList());
        final List<UaiQueryParam> queryParamList = UaiQueryParamFactory.create(callbackDTO.getQueryParamList());

        callback.setHeaderList(headerList);
        callback.setQueryParamList(queryParamList);

        return callback;
    }
}
