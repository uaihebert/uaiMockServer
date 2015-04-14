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
package test.com.uaihebert.uaimockserver.model;

import io.undertow.util.Headers;
import org.junit.Test;
import test.com.uaihebert.uaimockserver.TestAbstract;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UaiMockServerTest extends TestAbstract {

    @Test
    public void isGetRootReturning200() {
        final String url = "http://localhost:1234/uaiMockServer/";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void isPutReturning201() {
        final String url = "http://localhost:1234/uaiMockServer/customer";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().put(createEntityTO());

        assertEquals(201, response.getStatus());
    }

    @Test
    public void isReturningDefaultContentType() {
        final String url = "http://localhost:1234/uaiMockServer/noResponseContentTypeConfigured";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(204, response.getStatus());
        assertEquals("making sure that the defaultContentTypeWasReturned", "text/html;charset=UTF-8", response.getHeaderString(Headers.CONTENT_TYPE.toString()));
    }

    @Test
    public void isReturningHeaderWithOneValue() {
        final String url = "http://localhost:1234/uaiMockServer/responseHeaderTest";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(204, response.getStatus());
        assertEquals("making sure that the server returned the header", "RETURNED_HEADER-01", response.getHeaderString("HEADER-01"));
    }

    @Test
    public void isReturningHeaderWithValueList() {
        final String url = "http://localhost:1234/uaiMockServer/responseHeaderTest";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(204, response.getStatus());

        final MultivaluedMap<String, Object> headerMap = response.getHeaders();
        final List<Object> headerList = headerMap.get("HEADER-02");

        final List<String> expectedResponse = Arrays.asList("LIST_01", "LIST_02");

        assertTrue("making sure that the server returned the header", expectedResponse.contains(headerList.get(0)));
        assertTrue("making sure that the server returned the header", expectedResponse.contains(headerList.get(1)));
    }
}