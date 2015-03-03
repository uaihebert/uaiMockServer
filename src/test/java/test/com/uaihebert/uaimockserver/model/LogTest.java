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

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.server.UaiMockServer;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class LogTest {

    @Test
    public void invokingWarnMethodOnDeactivatedWarnForCoverage() {
        final URL resource = UaiMockServerConfig.class.getResource("/configWithoutLog.json");

        final UaiMockServer uaiMockServer = UaiMockServer.start(resource.getFile());

        final String url = "http://localhost:1234/uaiMockServer/noLog";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(500, response.getStatus());

        uaiMockServer.shutdown();
    }
}