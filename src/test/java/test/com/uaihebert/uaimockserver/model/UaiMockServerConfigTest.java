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
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.fail;

public class UaiMockServerConfigTest {

    @Test
    public void isCreatingServerConfig() {
        new UaiMockServerConfig();
    }

    @Test
    public void isCreatingWithFile() {
        final URL resource = UaiMockServerConfig.class.getResource("/uaiMockServer.config");

        new UaiMockServerConfig(resource.getFile());
    }

    @Test
    public void isCreatingWithoutRoutesInTheMainFile() {
        try {
            new UaiMockServerConfig("mainFileWithoutRoutes.config");
        } catch (final Exception ex) {
            ex.printStackTrace();
            fail("should not fail because the file has no routes");
        }
    }

    @Test
    public void isCreatingWithoutLog() {
        try {
            new UaiMockServerConfig("configWithoutLog.config");
        } catch (final Exception ex) {
            ex.printStackTrace();
            fail("should not fail because the file has no log");
        }
    }
}