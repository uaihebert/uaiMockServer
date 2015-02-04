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
package com.uaihebert.uaimockserver.main;

import com.uaihebert.uaimockserver.server.UaiMockServer;

/**
 * Only a bootstrap class.
 *
 * Right now there is no need to test it
 */
public class UaiMockServerMain {

    /**
     * It is possible just with a default file named 'uaiMockServer.config'
     * Or pass the name of the file like: java -jar uaiMockServer.jar myConfigFile.config
     */
    public static void main(String[] args) throws InterruptedException {
        startServer(args);

        Thread.currentThread().join();
    }

    private static void startServer(final String[] args) {
        if (args.length > 0) {
            UaiMockServer.start(args[0]);
            return;
        }

        UaiMockServer.start();
    }
}