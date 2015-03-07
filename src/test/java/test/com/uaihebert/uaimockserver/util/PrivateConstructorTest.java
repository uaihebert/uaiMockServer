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
import com.uaihebert.uaimockserver.dto.factory.WebSocketLogDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiBasicConfigurationDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiFileDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiHeaderDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiQueryParamDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiRequestDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiResponseDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiRouteDTOFactory;
import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.factory.UaiHeaderFactory;
import com.uaihebert.uaimockserver.factory.UaiMockServerConfigFactory;
import com.uaihebert.uaimockserver.factory.UaiQueryParamFactory;
import com.uaihebert.uaimockserver.factory.UaiRequestFactory;
import com.uaihebert.uaimockserver.factory.UaiResponseFactory;
import com.uaihebert.uaimockserver.factory.UaiRouteFactory;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.log.backend.LogBuilder;
import com.uaihebert.uaimockserver.log.gui.UaiWebSocketLog;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.repository.UaiRouteMapper;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import com.uaihebert.uaimockserver.service.UaiRouteService;
import com.uaihebert.uaimockserver.util.ExceptionUtil;
import com.uaihebert.uaimockserver.util.FileUtil;
import com.uaihebert.uaimockserver.util.HttpServerUtil;
import com.uaihebert.uaimockserver.util.RequestBodyExtractor;
import com.uaihebert.uaimockserver.util.RequestHolder;
import com.uaihebert.uaimockserver.util.RouteFinderUtil;
import com.uaihebert.uaimockserver.util.RouteMapKeyUtil;
import com.uaihebert.uaimockserver.util.StringUtils;
import com.uaihebert.uaimockserver.validator.RequestValidator;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertNotNull;

public class PrivateConstructorTest {

    @Test
    public void testUaiRouteFactoryConstructor() throws Exception {
        final Constructor<UaiRouteFactory> constructor = UaiRouteFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiRouteFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiRequestFactoryConstructor() throws Exception {
        final Constructor<UaiRequestFactory> constructor = UaiRequestFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiRequestFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testHttpServerUtilConstructor() throws Exception {
        final Constructor<HttpServerUtil> constructor = HttpServerUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final HttpServerUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRequestValidatorConstructor() throws Exception {
        final Constructor<RequestValidator> constructor = RequestValidator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RequestValidator createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiResponseFactoryConstructor() throws Exception {
        final Constructor<UaiResponseFactory> constructor = UaiResponseFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiResponseFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiHeaderFactoryConstructor() throws Exception {
        final Constructor<UaiHeaderFactory> constructor = UaiHeaderFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiHeaderFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiQueryParamFactoryConstructor() throws Exception {
        final Constructor<UaiQueryParamFactory> constructor = UaiQueryParamFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiQueryParamFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRouteMapKeyUtilConstructor() throws Exception {
        final Constructor<RouteMapKeyUtil> constructor = RouteMapKeyUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RouteMapKeyUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testLogFactoryConstructor() throws Exception {
        final Constructor<LogBuilder> constructor = LogBuilder.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final LogBuilder createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testExceptionUtilConstructor() throws Exception {
        final Constructor<ExceptionUtil> constructor = ExceptionUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final ExceptionUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRouteFinderUtilConstructor() throws Exception {
        final Constructor<RouteFinderUtil> constructor = RouteFinderUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RouteFinderUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRequestValidatorFacadeConstructor() throws Exception {
        final Constructor<RequestValidatorFacade> constructor = RequestValidatorFacade.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RequestValidatorFacade createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRequestHolderConstructor() throws Exception {
        final Constructor<RequestHolder> constructor = RequestHolder.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RequestHolder createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiRequestDTOFactoryConstructor() throws Exception {
        final Constructor<UaiRequestDTOFactory> constructor = UaiRequestDTOFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiRequestDTOFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }
    
    @Test
    public void testUaiQueryParamDTOFactoryConstructor() throws Exception {
        final Constructor<UaiQueryParamDTOFactory> constructor = UaiQueryParamDTOFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiQueryParamDTOFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiHeaderDTOFactoryConstructor() throws Exception {
        final Constructor<UaiHeaderDTOFactory> constructor = UaiHeaderDTOFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiHeaderDTOFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiResponseDTOFactoryConstructor() throws Exception {
        final Constructor<UaiResponseDTOFactory> constructor = UaiResponseDTOFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiResponseDTOFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiRouteDTOFactoryConstructor() throws Exception {
        final Constructor<UaiRouteDTOFactory> constructor = UaiRouteDTOFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiRouteDTOFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testStringUtilConstructor() throws Exception {
        final Constructor<StringUtils> constructor = StringUtils.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final StringUtils createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testLogConstructor() throws Exception {
        final Constructor<Log> constructor = Log.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final Log createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testFileUtilConstructor() throws Exception {
        final Constructor<FileUtil> constructor = FileUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final FileUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiFileDTOFactoryConstructor() throws Exception {
        final Constructor<UaiFileDTOFactory> constructor = UaiFileDTOFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiFileDTOFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRequestBodyExtractorConstructor() throws Exception {
        final Constructor<RequestBodyExtractor> constructor = RequestBodyExtractor.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RequestBodyExtractor createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiRouteHelperConstructor() throws Exception {
        final Constructor<UaiRouteService> constructor = UaiRouteService.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiRouteService createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiMockServerConfigConstructor() throws Exception {
        final Constructor<UaiMockServerConfig> constructor = UaiMockServerConfig.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiMockServerConfig createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testBasicConfigurationDTOFactoryConstructor() throws Exception {
        final Constructor<UaiBasicConfigurationDTOFactory> constructor = UaiBasicConfigurationDTOFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiBasicConfigurationDTOFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiMockServerConfigFactoryConstructor() throws Exception {
        final Constructor<UaiMockServerConfigFactory> constructor = UaiMockServerConfigFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiMockServerConfigFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiRouteMapperConstructor() throws Exception {
        final Constructor<UaiRouteMapper> constructor = UaiRouteMapper.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiRouteMapper createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiRouteRepositoryConstructor() throws Exception {
        final Constructor<UaiRouteRepository> constructor = UaiRouteRepository.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiRouteRepository createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiWebSocketContextConstructor() throws Exception {
        final Constructor<UaiWebSocketContext> constructor = UaiWebSocketContext.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiWebSocketContext createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testLogDTOFactoryConstructor() throws Exception {
        final Constructor<WebSocketLogDTOFactory> constructor = WebSocketLogDTOFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final WebSocketLogDTOFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testWebSocketLogConstructor() throws Exception {
        final Constructor<UaiWebSocketLog> constructor = UaiWebSocketLog.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiWebSocketLog createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }
}