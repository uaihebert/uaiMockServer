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
package test.com.uaihebert.uaimockserver.validation;

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.server.UaiMockServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URL;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class HoldTheRequestTest {
    private static UaiMockServer uaiMockServer;

    private static final String ASSERTING_TEXT = "asserting that the time lapse with hold is >= than regular request";
    private static final int MIN_TIME_WAITTING = 490;

    @BeforeClass
    public static void init() {
        final URL resource = UaiMockServerConfig.class.getResource("/holdRequest.config");

        uaiMockServer = UaiMockServer.start(resource.getFile());
    }

    @AfterClass
    public static void after() {
        uaiMockServer.shutdown();
    }

    @Test
    public void isHoldingRequest() {
        getRequestLapseWithNoHold(); // bootstrap request is slower than the next requests

        final long timeLapseRegularRequest = getRequestLapseWithNoHold();
        final long timeLapseRequestWithHold = getRequestLapseWithHold();

        final long regularRequestWithHoldTime = timeLapseRegularRequest + MIN_TIME_WAITTING;

        assertTrue(ASSERTING_TEXT, timeLapseRequestWithHold >= regularRequestWithHoldTime);
    }

    private long getRequestLapseWithNoHold() {
        final Date beforeRequest = new Date();

        final String url = "http://localhost:1234/uaiMockServer/doNotHoldTheRequest";

        Client client = ClientBuilder.newClient();

        client.target(url).request().get();

        final Date afterRequest = new Date();

        return afterRequest.getTime() - beforeRequest.getTime();
    }

    private long getRequestLapseWithHold() {
        final Date beforeRequest = new Date();

        final String url = "http://localhost:1234/uaiMockServer/holdTheRequest";

        Client client = ClientBuilder.newClient();

        client.target(url).request().get();

        final Date afterRequest = new Date();

        return afterRequest.getTime() - beforeRequest.getTime();
    }
}