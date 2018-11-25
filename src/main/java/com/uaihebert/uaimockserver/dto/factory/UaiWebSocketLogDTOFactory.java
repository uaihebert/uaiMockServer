package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiLogPairValueDTO;
import com.uaihebert.uaimockserver.dto.model.UaiWebSocketLogCallbackDTO;
import com.uaihebert.uaimockserver.dto.model.UaiWebSocketLogDTO;
import com.uaihebert.uaimockserver.dto.model.UaiWebSocketLogRequestDTO;
import com.uaihebert.uaimockserver.model.UaiCallback;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public final class UaiWebSocketLogDTOFactory {
    private UaiWebSocketLogDTOFactory() {
    }

    public static UaiWebSocketLogDTO create(final UaiCallback callback) {
        final UaiWebSocketLogCallbackDTO logDTO = new UaiWebSocketLogCallbackDTO();
        logDTO.setBodyToSend(callback.getBodyToSend());
        logDTO.setCompleteUrlToCall(callback.getCompleteUrlToCall());
        logDTO.setDelayInMilli(callback.getDelayInMilli());
        logDTO.setHttpMethod(callback.getHttpMethod());
        logDTO.setHeaderList(UaiHeaderDTOFactory.create(callback.getHeaderList()));
        logDTO.setQueryParamList(UaiQueryParamDTOFactory.create(callback.getQueryParamList()));

        final UaiWebSocketLogRequestDTO logRequestDTO = new UaiWebSocketLogRequestDTO();
        logRequestDTO.setArrivedAt(getFormattedDate());

        final UaiWebSocketLogDTO uaiWebSocket = new UaiWebSocketLogDTO();
        uaiWebSocket.setLogCallback(logDTO);
        uaiWebSocket.setLogRequest(logRequestDTO);

        return uaiWebSocket;
    }

    public static UaiWebSocketLogDTO create(final HttpServerExchange exchange) {
        final UaiWebSocketLogDTO uaiWebSocketLogDTO = new UaiWebSocketLogDTO();

        final UaiWebSocketLogRequestDTO logRequestDTO = createLogRequest(exchange);

        uaiWebSocketLogDTO.setLogRequest(logRequestDTO);

        return uaiWebSocketLogDTO;
    }

    private static UaiWebSocketLogRequestDTO createLogRequest(final HttpServerExchange exchange) {
        final UaiWebSocketLogRequestDTO logRequestDTO = new UaiWebSocketLogRequestDTO();

        logRequestDTO.setMethod(exchange.getRequestMethod().toString());
        logRequestDTO.setArrivedAt(getFormattedDate());
        logRequestDTO.setWhoInvokedAddress(exchange.getSourceAddress().getAddress().toString());

        logRequestDTO.setPath(exchange.getRequestPath());

        final HeaderValues contentType = exchange.getRequestHeaders().get("Content-Type");
        if (contentType != null) {
            logRequestDTO.setContentType(contentType.getFirst());
        }

        createHeaderList(exchange, logRequestDTO);

        createQueryParamList(exchange, logRequestDTO);

        return logRequestDTO;
    }

    private static String getFormattedDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    private static void createQueryParamList(final HttpServerExchange exchange,
                                             final UaiWebSocketLogRequestDTO logRequestDTO) {
        for (Map.Entry<String, Deque<String>> queryParamValue : exchange.getQueryParameters().entrySet()) {
            final String queryParamName = queryParamValue.getKey();
            final Deque<String> stringDeque = queryParamValue.getValue();

            final List<String> valueList = new ArrayList<String>(stringDeque);

            final UaiLogPairValueDTO pairValueDTO = new UaiLogPairValueDTO(queryParamName, valueList);
            logRequestDTO.getQueryParamValueList().add(pairValueDTO);
        }
    }

    private static void createHeaderList(final HttpServerExchange exchange,
                                         final UaiWebSocketLogRequestDTO logRequestDTO) {
        for (HeaderValues headerValues : exchange.getRequestHeaders()) {
            final String headerName = headerValues.getHeaderName().toString();

            final String[] valueAsArray = headerValues.toArray();

            final UaiLogPairValueDTO pairValueDTO = new UaiLogPairValueDTO(headerName, Arrays.asList(valueAsArray));

            logRequestDTO.getHeaderValueList().add(pairValueDTO);
        }
    }
}
