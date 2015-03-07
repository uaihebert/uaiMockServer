package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.WebSocketLogDTO;
import com.uaihebert.uaimockserver.dto.model.UaiLogPairValueDTO;
import com.uaihebert.uaimockserver.dto.model.UaiLogRequestDTO;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class WebSocketLogDTOFactory {
    private WebSocketLogDTOFactory() {
    }

    public static WebSocketLogDTO create(final HttpServerExchange exchange) {
        WebSocketLogDTO webSocketLogDTO = new WebSocketLogDTO();

        final UaiLogRequestDTO logRequestDTO = createLogRequest(exchange);

        webSocketLogDTO.setLogRequest(logRequestDTO);

        return webSocketLogDTO;
    }

    private static UaiLogRequestDTO createLogRequest(final HttpServerExchange exchange) {
        final UaiLogRequestDTO logRequestDTO = new UaiLogRequestDTO();

        // todo refactor here
        logRequestDTO.setArrivedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        logRequestDTO.setWhoInvokedAddress(exchange.getSourceAddress().getAddress().toString());

        logRequestDTO.setPath(exchange.getRequestPath());

        if (exchange.getRequestHeaders().get("Content-Type") != null) {
            logRequestDTO.setContentType(exchange.getRequestHeaders().get("Content-Type").getFirst());
        }

        for (HeaderValues headerValues : exchange.getRequestHeaders()) {
            final String headerName = headerValues.getHeaderName().toString();

            final String[] valueAsArray = headerValues.toArray();

            final UaiLogPairValueDTO pairValueDTO = new UaiLogPairValueDTO(headerName, Arrays.asList(valueAsArray));

            logRequestDTO.getHeaderValueList().add(pairValueDTO);
        }

        for (Map.Entry<String, Deque<String>> queryParamValue : exchange.getQueryParameters().entrySet()) {
            final String queryParamName = queryParamValue.getKey();
            final Deque<String> stringDeque = queryParamValue.getValue();

            final List<String> valueList = new ArrayList<String>();

            final Iterator<String> dequeIterator = stringDeque.iterator();

            while(dequeIterator.hasNext()) {
                final String value = dequeIterator.next();
                valueList.add(value);
            }

            final UaiLogPairValueDTO pairValueDTO = new UaiLogPairValueDTO(queryParamName, valueList);
            logRequestDTO.getQueryParamValueList().add(pairValueDTO);
        }


        return logRequestDTO;
    }
}