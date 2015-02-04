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
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class BodyValidationTest extends TestAbstract {

    @Test
    public void isPostWithRequiredBodyReturning204() {
        final String url = "http://localhost:1234/uaiMockServer/payment/33";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(createEntityTO());

        assertEquals(204, response.getStatus());
    }

    @Test
    public void isPostWithRequiredBodyReturningErrorIfBodyNotPresent() {
        final String url = "http://localhost:1234/uaiMockServer/payment/33";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("", MediaType.APPLICATION_JSON_TYPE));

        assertEquals(500, response.getStatus());
    }
}