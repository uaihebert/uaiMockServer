package com.uaihebert.uaimockserver.context;

import com.uaihebert.uaimockserver.dto.model.UaiWebSocketLogDTO;
import com.uaihebert.uaimockserver.util.JsonUtil;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;

import java.util.ArrayList;
import java.util.List;

public final class UaiWebSocketContext {
    private UaiWebSocketContext() {
    }

    private static final List<WebSocketChannel> CLIENT_LIST = new ArrayList<WebSocketChannel>();

    public static void addClient(final WebSocketChannel newClient) {
        synchronized (CLIENT_LIST){
            CLIENT_LIST.add(newClient);
        }
    }

    public static void removeClosed() {
        synchronized (CLIENT_LIST) {
            int toBeRemoved = -1;

            for (int i = 0; i < CLIENT_LIST.size(); i++) {
                if (!CLIENT_LIST.get(i).isOpen()) {
                    toBeRemoved = i;
                    break;
                }
            }

            CLIENT_LIST.remove(toBeRemoved);
        }
    }

    public static void sendLog(final UaiWebSocketLogDTO uaiWebSocketLogDTO) {
        final String entityAsString = JsonUtil.toJsonWithNoEscaping(uaiWebSocketLogDTO);

        for (WebSocketChannel clientChannel : CLIENT_LIST) {
            WebSockets.sendText(entityAsString, clientChannel, null);
        }
    }
}