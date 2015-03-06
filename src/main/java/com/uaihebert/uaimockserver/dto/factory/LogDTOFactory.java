package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.LogDTO;
import com.uaihebert.uaimockserver.dto.model.UaiLogPairValueDTO;
import com.uaihebert.uaimockserver.dto.model.UaiLogRequestDTO;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class LogDTOFactory {
    private LogDTOFactory() {
    }

    public static LogDTO create(final HttpServerExchange exchange) {
        LogDTO logDTO = new LogDTO();

        final UaiLogRequestDTO logRequestDTO = createLogRequest(exchange);

        logDTO.setLogRequestDTO(logRequestDTO);

        return logDTO;
    }

    private static UaiLogRequestDTO createLogRequest(final HttpServerExchange exchange) {
        final UaiLogRequestDTO logRequestDTO = new UaiLogRequestDTO();

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