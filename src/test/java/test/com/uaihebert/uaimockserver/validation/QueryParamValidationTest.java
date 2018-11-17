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

import com.uaihebert.uaimockserver.model.HttpStatusCode;
import org.junit.Test;
import test.com.uaihebert.uaimockserver.TestAbstract;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class QueryParamValidationTest extends TestAbstract {

    @Test
    public void isGetWithoutRequiredQueryParamReturningError() {
        final String url = "http://localhost:1234/uaiMockServer/requiredQueryParam";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().get();

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getStatus());
    }

    @Test
    public void isGetWithRequiredQueryParamWithoutValueReturningError() {
        final String url = "http://localhost:1234/uaiMockServer/requiredQueryParam?queryParam01=REQUIRED&queryParam02=bbb";

        final Client client = ClientBuilder.newClient();

        final Response response = client
            .target(url)
            .request()
            .get();

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getStatus());
    }

    @Test
    public void isGetWithRequiredQueryParamWorking() {
        final String url = "http://localhost:1234/uaiMockServer/requiredQueryParam?queryParam01=MOCK_VALUE&queryParam01=REQUIRED&queryParam02=MOCK_VALUE&queryParam02=REQUIRED";

        final Client client = ClientBuilder.newClient();

        final Response response = client
            .target(url)
            .request()
            .get();

        assertEquals(HttpStatusCode.OK.code, response.getStatus());
    }

    @Test
    public void isGetWithWildcardQueryParamWorking() {
        final String url = "http://localhost:1234/uaiMockServer/wildCardIsWorkingWithQueryParam?queryParam01=USING_WILD_CARD&queryParam01=REQUIRED&queryParam02=USING_WILD_CARD";

        final Client client = ClientBuilder.newClient();

        final Response response = client
            .target(url)
            .request()
            .get();

        assertEquals(HttpStatusCode.OK.code, response.getStatus());
    }
}
