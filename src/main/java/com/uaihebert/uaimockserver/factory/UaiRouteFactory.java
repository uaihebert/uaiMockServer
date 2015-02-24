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
package com.uaihebert.uaimockserver.factory;

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.model.UaiFile;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.model.UaiResponse;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.FileUtil;

import java.io.File;

/**
 * This factory will create an instance of the UaiRoute.java
 */
public final class UaiRouteFactory {
    private UaiRouteFactory() {
    }

    public static UaiRoute create(final Config routeConfig, final UaiMockServerConfig uaiMockServerConfig, final File file) {
        final UaiRequest uaiRequest = UaiRequestFactory.create(routeConfig);

        final UaiResponse uaiResponse = UaiResponseFactory.create(routeConfig, uaiMockServerConfig);

        final UaiFile uaiFile = new UaiFile(FileUtil.getNameWithoutExtension(file), file.getAbsolutePath());

        return new UaiRoute(uaiFile, uaiRequest, uaiResponse);
    }
}