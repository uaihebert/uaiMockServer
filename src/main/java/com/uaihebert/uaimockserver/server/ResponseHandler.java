/*
 * Copyright 2015 uaiHebert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
package com.uaihebert.uaimockserver.server;

import com.uaihebert.uaimockserver.configuration.ProjectConfiguration;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiResponse;
import com.uaihebert.uaimockserver.util.FileUtil;
import com.uaihebert.uaimockserver.util.StringUtils;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;

import java.io.*;

class ResponseHandler {
    public void process(final HttpServerExchange exchange, final UaiResponse uaiResponse) {
        Log.infoFormatted("Response that will be sent: [%s]", uaiResponse);

        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, uaiResponse.getContentType());

        exchange.setResponseCode(uaiResponse.getStatusCode());

        setResponseHeaders(uaiResponse, exchange);

        defineResponseBody(exchange, uaiResponse);
    }

    private void defineResponseBody(HttpServerExchange exchange, UaiResponse uaiResponse) {
        if (!uaiResponse.isBodyPointingToFile() && uaiResponse.getBody() != null) {
            exchange.getResponseSender().send(uaiResponse.getBody());
            return;
        }

        if (uaiResponse.isBodyPointingToFile() && StringUtils.isNotBlank(uaiResponse.getBodyPath())) {
            final File file = FileUtil.findFile(uaiResponse.getBodyPath());

            try {
                exchange.startBlocking();
                printResource(exchange.getOutputStream(), new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setResponseHeaders(final UaiResponse uaiResponse, final HttpServerExchange exchange) {
        for (UaiHeader uaiHeader : uaiResponse.getHeaderList()) {
            for (String value : uaiHeader.getValueList()) {
                exchange.getResponseHeaders().add(new HttpString(uaiHeader.getName()), value);
            }
        }
    }

    void printResource(final OutputStream writer, final InputStream inputStream) throws IOException {
        final InputStreamReader streamReader = new InputStreamReader(inputStream, ProjectConfiguration.ENCODING.value);
        final BufferedReader bufferedReader = new BufferedReader(streamReader);

        try {
            byte[] buf = new byte[1024];
            int count;
            while ((count = inputStream.read(buf)) >= 0) {
                writer.write(buf, 0, count);
            }

            writer.flush();
        } finally {
            writer.close();
            streamReader.close();
            bufferedReader.close();
        }
    }

}