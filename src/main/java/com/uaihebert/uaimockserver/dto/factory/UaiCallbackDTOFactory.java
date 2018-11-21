package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiCallbackDTO;
import com.uaihebert.uaimockserver.dto.model.UaiHeaderDTO;
import com.uaihebert.uaimockserver.dto.model.UaiQueryParamDTO;
import com.uaihebert.uaimockserver.model.UaiCallback;

import java.util.List;

public final class UaiCallbackDTOFactory {
    private UaiCallbackDTOFactory() {
    }

    public static UaiCallbackDTO create(final UaiCallback callback) {
        if (callback == null) {
            return null;
        }

        final UaiCallbackDTO callbackDTO = new UaiCallbackDTO();
        callbackDTO.setBodyToSend(callback.getBodyToSend());
        callbackDTO.setCompleteUrlToCall(callback.getCompleteUrlToCall());
        callbackDTO.setDelayInMilli(callback.getDelayInMilli());
        callbackDTO.setHttpMethod(callback.getHttpMethod());

        final List<UaiQueryParamDTO> queryParams = UaiQueryParamDTOFactory.create(
            callback.getQueryParamList()
        );

        final List<UaiHeaderDTO> headers = UaiHeaderDTOFactory.create(
            callback.getHeaderList()
        );

        callbackDTO.setHeaderList(headers);
        callbackDTO.setQueryParamList(queryParams);

        return callbackDTO;
    }
}
