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
package test.com.uaihebert.uaimockserver;

import com.uaihebert.uaimockserver.server.UaiMockServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public abstract class TestAbstract {

    private static UaiMockServer uaiMockServer;

    @BeforeClass
    public static void beforeClass() {
        uaiMockServer = UaiMockServer.start();
    }

    @AfterClass
    public static void afterClass() {
        uaiMockServer.shutdown();
    }

    public Entity<String> createEntityTO(){
        return Entity.entity("{'name':'joseph'}", MediaType.APPLICATION_JSON_TYPE);
    }
}