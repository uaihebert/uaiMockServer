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
package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRequest;

/**
 * Class with methods that will help to create the RouteKei.
 * The key is create to optimize the search for it when a request arrives.
 */
public final class RouteMapKeyUtil {
    private RouteMapKeyUtil() {
    }

    public static String createKeyForMap(final UaiRequest uaiRequest, final UaiMockServerConfig uaiMockServerConfig){
        final String uri = uaiMockServerConfig.basicConfiguration.context + uaiRequest.path;

        uaiMockServerConfig.basicConfiguration.log.infoFormatted("Route detected: method [%s] and URI [%s] ", uaiRequest.method, uri);

        return uaiRequest.method + "_" + uri;
    }
}
// TODO transform basicConfiguration into a context with Static attributes