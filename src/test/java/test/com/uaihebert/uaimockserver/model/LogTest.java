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

import com.uaihebert.uaimockserver.model.HttpStatusCode;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "configWithoutLog.json")
public class LogTest {

    @Test
    public void invokingWarnMethodOnDeactivatedWarnForCoverage() {
        final String url = "http://localhost:1234/uaiMockServer/noLog";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getStatus());
    }

    @Test
    public void invokingWarnMethodOnDeactivatedWarnWithExceptionForCoverage() {
        final String url = "http://localhost:1234/uaiMockServer/noLogUnknownWithNoLog";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getStatus());
    }
}
