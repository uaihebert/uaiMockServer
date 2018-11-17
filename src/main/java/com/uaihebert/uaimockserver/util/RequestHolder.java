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

import com.uaihebert.uaimockserver.log.backend.Log;

public final class RequestHolder {
    private static final String REQUEST_HOLDING_LOG = "Holding the request for [%s] milli seconds";

    private RequestHolder() {
    }

    public static void holdTheRequest(final Long timeToHold) {
        if (timeToHold == null || timeToHold < 1) {
            return;
        }

        try {
            Log.info(String.format(REQUEST_HOLDING_LOG, timeToHold));
            Thread.sleep(timeToHold);
        } catch (InterruptedException e) {
            throw new IllegalStateException("error while holding the request ", e);
        }
    }
}
