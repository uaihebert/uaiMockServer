package com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.context.UaiWebSocketContext;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.spi.WebSocketHttpExchange;

public class UaiWebSocketCallback implements WebSocketConnectionCallback {

    @Override
    public void onConnect(final WebSocketHttpExchange exchange, final WebSocketChannel channel) {
        channel.getReceiveSetter().set(new UaiWebSocketListener());

        UaiWebSocketContext.addClient(channel);

        channel.resumeReceives();
    }
}
