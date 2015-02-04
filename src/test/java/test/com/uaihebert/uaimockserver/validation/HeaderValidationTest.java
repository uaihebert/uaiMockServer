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

import org.junit.Test;
import test.com.uaihebert.uaimockserver.TestAbstract;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class HeaderValidationTest extends TestAbstract {

    @Test
    public void isGetWithoutRequiredHeaderReturningError(){
        final String url = "http://localhost:1234/uaiMockServer/requiredHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().get();

        assertEquals(500, response.getStatus());
    }

    @Test
    public void isGetWithRequiredHeaderWithoutValueReturningError(){
        final String url = "http://localhost:1234/uaiMockServer/requiredHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .header("X-UAI-LOGIN-HEADER", "MOCK_VALUE")
                .header("X-API-VERSION", "     ")
                .get();

        assertEquals(500, response.getStatus());
    }

    @Test
    public void isGetWithRequiredHeaderWorking(){
        final String url = "http://localhost:1234/uaiMockServer/requiredHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .header("X-UAI-LOGIN-HEADER", "MOCK_VALUE")
                .header("X-UAI-LOGIN-HEADER", "REQUIRED")
                .header("X-API-VERSION", "MOCK_VALUE")
                .header("X-API-VERSION", "REQUIRED")
                .get();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void isGetWithWildCardHeaderWorking(){
        final String url = "http://localhost:1234/uaiMockServer/wildCardIsWorkingWithHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .header("X-UAI-LOGIN-HEADER", "ANY_VALUE")
                .header("X-API-VERSION", "RANDOM_VALUE")
                .get();

        assertEquals(200, response.getStatus());
    }
}