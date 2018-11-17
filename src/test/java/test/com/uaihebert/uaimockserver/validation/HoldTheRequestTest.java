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

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "holdRequest.json")
public class HoldTheRequestTest {

    @SuppressWarnings("LineLength")
    private static final String ASSERTING_TEXT = "asserting that the time lapse with hold [%s] is >= than regular request [%s]";
    private static final int MIN_TIME_WAITING = 490;

    @Before
    public void before() {
        // the first request is slower than the next requests
        // this dummy request is just to activate the server
        getRequestLapseWithNoHold();
    }

    @Test
    public void isHoldingRequest() {
        final long timeLapseRegularRequest = getRequestLapseWithNoHold();
        final long timeLapseRequestWithHold = getRequestLapseWithHold();

        final long regularRequestWithHoldTime = timeLapseRegularRequest + MIN_TIME_WAITING;

        final String expected = String.format(ASSERTING_TEXT, timeLapseRequestWithHold, regularRequestWithHoldTime);
        assertTrue(expected, timeLapseRequestWithHold >= regularRequestWithHoldTime);
    }

    private long getRequestLapseWithNoHold() {
        final Date beforeRequest = new Date();

        final String url = "http://localhost:1234/uaiMockServer/doNotHoldTheRequest";

        final Client client = ClientBuilder.newClient();

        client.target(url).request().get();

        final Date afterRequest = new Date();

        return afterRequest.getTime() - beforeRequest.getTime();
    }

    private long getRequestLapseWithHold() {
        final Date beforeRequest = new Date();

        final String url = "http://localhost:1234/uaiMockServer/holdTheRequest";

        final Client client = ClientBuilder.newClient();

        client.target(url).request().get();

        final Date afterRequest = new Date();

        return afterRequest.getTime() - beforeRequest.getTime();
    }
}
