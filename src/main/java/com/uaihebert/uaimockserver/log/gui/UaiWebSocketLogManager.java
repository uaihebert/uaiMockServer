package com.uaihebert.uaimockserver.log.gui;

import com.uaihebert.uaimockserver.context.UaiWebSocketContext;
import com.uaihebert.uaimockserver.dto.factory.UaiWebSocketLogDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiWebSocketLogResponseDTOFactory;
import com.uaihebert.uaimockserver.dto.model.UaiWebSocketLogDTO;
import com.uaihebert.uaimockserver.dto.model.UaiWebSocketLogResponseDTO;
import com.uaihebert.uaimockserver.model.UaiResponse;
import io.undertow.server.HttpServerExchange;

public final class UaiWebSocketLogManager {
    private static final String ERROR_MESSAGE_BODY = "{\"errorMessage\": \"We have a problem with your request. Did you sent everything that was required? \n " +
            "The error message is: [%s]\"}";
    private static final ThreadLocal<UaiWebSocketLogDTO> THREAD_LOCAL = new ThreadLocal<UaiWebSocketLogDTO>();

    private UaiWebSocketLogManager() {
    }

    public static void start(final HttpServerExchange exchange) {
        final UaiWebSocketLogDTO uaiWebSocketLogDTO = UaiWebSocketLogDTOFactory.create(exchange);

        THREAD_LOCAL.set(uaiWebSocketLogDTO);
    }

    public static void addLogText(final String text) {
        getCurrentLog().addTextLog(text);
    }

    public static void log() {
        final UaiWebSocketLogDTO uaiWebSocketLogDTO = getCurrentLog();

        THREAD_LOCAL.remove();

        UaiWebSocketContext.sendLog(uaiWebSocketLogDTO);
    }

    private static UaiWebSocketLogDTO getCurrentLog() {
        final UaiWebSocketLogDTO uaiWebSocketLogDTO = THREAD_LOCAL.get();

        if (uaiWebSocketLogDTO == null) {
            // the tests and the GUI requests will not have the scope
            return new UaiWebSocketLogDTO();
        }

        return uaiWebSocketLogDTO;
    }

    public static void setResponse(final UaiResponse uaiResponse) {
        final UaiWebSocketLogResponseDTO logResponseDTO = UaiWebSocketLogResponseDTOFactory.create(uaiResponse);
        getCurrentLog().setLogResponse(logResponseDTO);
    }

    public static void exceptionDetected(final String message) {
        getCurrentLog().setFinishedWithError();

        final UaiWebSocketLogResponseDTO logResponseDTO = new UaiWebSocketLogResponseDTO();
        logResponseDTO.setStatusCode(500);
        logResponseDTO.setBody(String.format(ERROR_MESSAGE_BODY, message));

        getCurrentLog().setLogResponse(logResponseDTO);
    }
}