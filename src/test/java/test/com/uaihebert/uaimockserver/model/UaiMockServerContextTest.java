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

import com.uaihebert.uaimockserver.model.UaiFile;
import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.model.UaiRoute;
import org.junit.Test;
import org.pmw.tinylog.Logger;


import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

@SuppressWarnings("IllegalCatch")
public class UaiMockServerContextTest {

    @Test
    public void isCreatingServerConfig() {
        UaiMockServerContext.createInstance();
    }

    @Test
    public void isCreatingWithFile() {
        UaiMockServerContext.createInstance("uaiMockServer.json");
    }

    @Test
    public void isCreatingWithoutRoutesInTheMainFile() {
        try {
            UaiMockServerContext.createInstance("mainFileWithoutRoutes.json");
        } catch (final Exception ex) {
            Logger.info(ex);
            fail("should not fail because the file has no routes");
        }
    }

    @Test
    public void isCreatingWithoutLog() {
        try {
            UaiMockServerContext.createInstance("configWithoutLog.json");
        } catch (final Exception ex) {
            Logger.info(ex);
            fail("should not fail because the file has no log");
        }
    }

    @Test
    public void isDeletingFromSecondaryMapping() {
        UaiMockServerContext.createInstance("uaiMockServer.json");

        final int totalBeforeDelete = UaiMockServerContext.getInstance()
            .secondaryMappingList
            .get(0)
            .getRouteList()
            .size();

        final UaiRoute uaiRoute = UaiMockServerContext.getInstance()
            .secondaryMappingList
            .get(0)
            .getRouteList()
            .get(0);

        UaiMockServerContext.getInstance().deleteRoute(uaiRoute);

        final int totalAfterDelete = UaiMockServerContext.getInstance()
            .secondaryMappingList
            .get(0)
            .getRouteList()
            .size();

        assertNotSame("making sure that the route was deleted", totalBeforeDelete, totalAfterDelete);
    }

    @Test
    public void thereIsNoErrorWhenNoSecondaryConfigIsFound() {
        UaiMockServerContext.createInstance("configWithoutFileMapList.json");

        try {
            final UaiRoute aRoute = UaiMockServerContext.getInstance().uaiMockServerConfig.getRouteList().get(0);
            UaiMockServerContext.getInstance().deleteRoute(aRoute);
        } catch (Exception ex) {
            Logger.info(ex);
            fail("should be no error");
        }
    }

    @Test
    public void thereIsNoErrorWhenTheRouteIsNotFound() {
        UaiMockServerContext.createInstance("uaiMockServer.json");

        try {
            final UaiRoute uaiRoute = new UaiRoute();
            uaiRoute.setUaiFile(new UaiFile("any", "any"));
            UaiMockServerContext.getInstance().deleteRoute(uaiRoute);
        } catch (Exception ex) {
            Logger.info(ex);
            fail("should be no error");
        }
    }
}
