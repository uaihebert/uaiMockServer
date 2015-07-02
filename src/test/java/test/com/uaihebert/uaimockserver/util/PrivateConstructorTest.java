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
import com.uaihebert.uaimockserver.validator.body.UaiJsonFieldFailureLogger;
import com.uaihebert.uaimockserver.validator.body.XmlUnitWrapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class PrivateConstructorTest {

    private static final List<Class> CLASSES_TO_TEST = new ArrayList<Class>();

    @BeforeClass
    public static void beforeClass() {
        CLASSES_TO_TEST.add(Log.class);
        CLASSES_TO_TEST.add(FileUtil.class);
        CLASSES_TO_TEST.add(JsonUtil.class);
        CLASSES_TO_TEST.add(LogBuilder.class);
        CLASSES_TO_TEST.add(StringUtils.class);
        CLASSES_TO_TEST.add(ExceptionUtil.class);
        CLASSES_TO_TEST.add(RequestHolder.class);
        CLASSES_TO_TEST.add(XmlUnitWrapper.class);
        CLASSES_TO_TEST.add(HttpServerUtil.class);
        CLASSES_TO_TEST.add(UaiRouteMapper.class);
        CLASSES_TO_TEST.add(RouteMapKeyUtil.class);
        CLASSES_TO_TEST.add(UaiRouteFactory.class);
        CLASSES_TO_TEST.add(RouteFinderUtil.class);
        CLASSES_TO_TEST.add(UaiRouteService.class);
        CLASSES_TO_TEST.add(UaiHeaderFactory.class);
        CLASSES_TO_TEST.add(UaiRequestFactory.class);
        CLASSES_TO_TEST.add(UaiFileDTOFactory.class);
        CLASSES_TO_TEST.add(PathHandlerFactory.class);
        CLASSES_TO_TEST.add(UaiResponseFactory.class);
        CLASSES_TO_TEST.add(UaiRouteRepository.class);
        CLASSES_TO_TEST.add(UaiRouteDTOFactory.class);
        CLASSES_TO_TEST.add(UaiWebSocketContext.class);
        CLASSES_TO_TEST.add(UaiHeaderDTOFactory.class);
        CLASSES_TO_TEST.add(UaiMockServerConfig.class);
        CLASSES_TO_TEST.add(UaiQueryParamFactory.class);
        CLASSES_TO_TEST.add(UaiRequestDTOFactory.class);
        CLASSES_TO_TEST.add(RequestBodyExtractor.class);
        CLASSES_TO_TEST.add(UaiRootContextService.class);
        CLASSES_TO_TEST.add(UaiJSONCompareWrapper.class);
        CLASSES_TO_TEST.add(UaiResponseDTOFactory.class);
        CLASSES_TO_TEST.add(UaiWebSocketLogManager.class);
        CLASSES_TO_TEST.add(RequestValidatorFacade.class);
        CLASSES_TO_TEST.add(UaiQueryParamDTOFactory.class);
        CLASSES_TO_TEST.add(UaiJsonFieldFailureLogger.class);
        CLASSES_TO_TEST.add(UaiWebSocketLogDTOFactory.class);
        CLASSES_TO_TEST.add(UaiMockServerConfigFactory.class);
        CLASSES_TO_TEST.add(UaiBasicConfigurationDTOFactory.class);
        CLASSES_TO_TEST.add(UaiWebSocketLogResponseDTOFactory.class);
    }

    @Test
    public void testConstructorList() throws Exception {
        for (Class<? extends Object> aClass : CLASSES_TO_TEST) {
            final Constructor<?> constructor = aClass.getDeclaredConstructor(new Class[0]);
            constructor.setAccessible(true);

            final Object createdObject = constructor.newInstance(new Object[0]);
            assertNotNull(createdObject);
        }
    }
}