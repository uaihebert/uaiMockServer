package com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.dto.model.UaiCallbackDTO;
import com.uaihebert.uaimockserver.model.UaiCallback;

public final class UaiCallbackFactory {
    private UaiCallbackFactory() {
    }

    public static UaiCallback create(final UaiCallbackDTO dto) {
        if (dto == null) {
            return null;
        }

        final UaiCallback callback = new UaiCallback();

        callback.setDelayInMilli(dto.getDelayInMilli());
        callback.setHttpMethod(dto.getHttpMethod());
        callback.setBodyToSend(dto.getBodyToSend());
        callback.setCompleteUrlToCall(dto.getCompleteUrlToCall());

        return callback;
    }
}
