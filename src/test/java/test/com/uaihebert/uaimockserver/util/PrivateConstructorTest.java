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

import com.uaihebert.uaimockserver.dto.factory.*;
import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.factory.*;
import com.uaihebert.uaimockserver.log.Log;
import com.uaihebert.uaimockserver.log.LogBuilder;
import com.uaihebert.uaimockserver.util.*;
import com.uaihebert.uaimockserver.validator.*;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertNotNull;

public class PrivateConstructorTest {

    @Test
    public void testRouteUtilConstructor() throws Exception {
        final Constructor<RouteUtil> constructor = RouteUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RouteUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

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
    public void testConfigKeyUtilConstructor() throws Exception {
        final Constructor<ConfigKeyUtil> constructor = ConfigKeyUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final ConfigKeyUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testTypeSafeConfigFactoryConstructor() throws Exception {
        final Constructor<TypeSafeConfigFactory> constructor = TypeSafeConfigFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final TypeSafeConfigFactory createdObject = constructor.newInstance(new Object[0]);
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
    public void testFileUtil() throws Exception {
        final Constructor<FileUtil> constructor = FileUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final FileUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiFileDTOFactory() throws Exception {
        final Constructor<UaiFileDTOFactory> constructor = UaiFileDTOFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiFileDTOFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }
}