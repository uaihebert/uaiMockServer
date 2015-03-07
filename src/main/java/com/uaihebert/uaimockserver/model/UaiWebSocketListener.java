package com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.context.UaiWebSocketContext;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.StreamSourceFrameChannel;
import io.undertow.websockets.core.WebSocketChannel;

import java.io.IOException;

public class UaiWebSocketListener extends AbstractReceiveListener {

    @Override
    protected void onClose(final WebSocketChannel webSocketChannel, final StreamSourceFrameChannel channel) throws IOException {
        super.onClose(webSocketChannel, channel);

        UaiWebSocketContext.removeClosed();
    }
}