package com.uaihebert.uaimockserver.log.gui;

import com.uaihebert.uaimockserver.context.UaiWebSocketContext;
import com.uaihebert.uaimockserver.dto.factory.WebSocketLogDTOFactory;
import com.uaihebert.uaimockserver.dto.model.WebSocketLogDTO;
import io.undertow.server.HttpServerExchange;

public final class UaiWebSocketLog {
    private static final ThreadLocal<WebSocketLogDTO> THREAD_LOCAL = new ThreadLocal<WebSocketLogDTO>();

    private UaiWebSocketLog() {
    }

    public static void start(final HttpServerExchange exchange) {
        final WebSocketLogDTO webSocketLogDTO = WebSocketLogDTOFactory.create(exchange);

        THREAD_LOCAL.set(webSocketLogDTO);
    }

    public static void addLogText(final String text) {
        getCurrentLog().addTextLog(text);
    }

    public static void log() {
        final WebSocketLogDTO webSocketLogDTO = getCurrentLog();

        THREAD_LOCAL.remove();

        UaiWebSocketContext.sendLog(webSocketLogDTO);
    }

    private static WebSocketLogDTO getCurrentLog() {
        final WebSocketLogDTO webSocketLogDTO = THREAD_LOCAL.get();

        if (webSocketLogDTO == null) {
            // the tests and the GUI requests will not have the scope
            return new WebSocketLogDTO();
        }

        return webSocketLogDTO;
    }
}