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

package test.com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.context.UaiWebSocketContext;
import com.uaihebert.uaimockserver.dto.factory.UaiBasicConfigurationDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiFileDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiHeaderDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiQueryParamDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiRequestDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiResponseDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiRouteDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiWebSocketLogDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiWebSocketLogResponseDTOFactory;
import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.factory.UaiHeaderFactory;
import com.uaihebert.uaimockserver.factory.UaiMockServerConfigFactory;
import com.uaihebert.uaimockserver.factory.UaiQueryParamFactory;
import com.uaihebert.uaimockserver.factory.UaiRequestFactory;
import com.uaihebert.uaimockserver.factory.UaiResponseFactory;
import com.uaihebert.uaimockserver.factory.UaiRouteFactory;
import com.uaihebert.uaimockserver.factory.undertow.PathHandlerFactory;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.log.backend.LogBuilder;
import com.uaihebert.uaimockserver.log.gui.UaiWebSocketLogManager;
import com.uaihebert.uaimockserver.main.UaiMockServerMain;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.repository.UaiRouteMapper;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import com.uaihebert.uaimockserver.service.UaiRootContextService;
import com.uaihebert.uaimockserver.service.UaiRouteService;
import com.uaihebert.uaimockserver.util.ExceptionUtil;
import com.uaihebert.uaimockserver.util.FileUtil;
import com.uaihebert.uaimockserver.util.HttpServerUtil;
import com.uaihebert.uaimockserver.util.JsonUtil;
import com.uaihebert.uaimockserver.util.RequestBodyExtractor;
import com.uaihebert.uaimockserver.util.RequestHolder;
import com.uaihebert.uaimockserver.util.RouteFinderUtil;
import com.uaihebert.uaimockserver.util.RouteMapKeyUtil;
import com.uaihebert.uaimockserver.util.StringUtils;
import com.uaihebert.uaimockserver.validator.body.UaiJSONCompareWrapper;
import com.uaihebert.uaimockserver.validator.body.XmlUnitWrapper;
import com.uaihebert.uaimockserver.validator.body.UaiJsonFieldFailureLogger;
import org.junit.Test;
import org.pmw.tinylog.Logger;
import test.com.uaihebert.uaimockserver.RunServerManually;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertNotNull;

public class PrivateConstructorTest {
    private Class[] classesWithPrivateConstructors = new Class[]{
        Log.class, FileUtil.class, JsonUtil.class, LogBuilder.class, StringUtils.class, ExceptionUtil.class,
        HttpServerUtil.class, RequestHolder.class, XmlUnitWrapper.class, UaiRouteMapper.class, RouteFinderUtil.class,
        UaiRouteService.class, UaiRouteFactory.class, RouteMapKeyUtil.class, UaiHeaderFactory.class,
        UaiRequestFactory.class, UaiFileDTOFactory.class, UaiResponseFactory.class, UaiRouteDTOFactory.class,
        UaiRouteRepository.class, PathHandlerFactory.class, UaiMockServerConfig.class, UaiWebSocketContext.class,
        UaiHeaderDTOFactory.class, RequestBodyExtractor.class, UaiQueryParamFactory.class, UaiRequestDTOFactory.class,
        UaiResponseDTOFactory.class, UaiJSONCompareWrapper.class, UaiRootContextService.class,
        UaiWebSocketLogManager.class, RequestValidatorFacade.class, UaiQueryParamDTOFactory.class,
        UaiWebSocketLogDTOFactory.class, UaiJsonFieldFailureLogger.class, UaiMockServerConfigFactory.class,
        UaiBasicConfigurationDTOFactory.class, UaiWebSocketLogResponseDTOFactory.class, RunServerManually.class,
        UaiMockServerMain.class,
    };

    @Test
    public <T> void testClassesPrivateConstructor() throws Exception {
        for (final Class<T> classToTest : classesWithPrivateConstructors) {
            Logger.info("Testing private constructor for: " + classToTest.getName());

            final Constructor<T> constructor = classToTest.getDeclaredConstructor(new Class[0]);
            constructor.setAccessible(true);

            final T createdObject = constructor.newInstance(new Object[0]);
            assertNotNull(createdObject);
        }
    }
}
