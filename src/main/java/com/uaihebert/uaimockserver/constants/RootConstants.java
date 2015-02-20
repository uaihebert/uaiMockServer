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
package com.uaihebert.uaimockserver.constants;

/**
 * Constants with the root attribute names
 */
public enum RootConstants {
    HOST("host"),
    PORT("port"),
    ROUTES("routes"),
    CONTEXT("context"),
    FILE_LOG("fileLog"),
    CONSOLE_LOG("consoleLog"),
    MAPPING_ROUTES_FILE_LIST("mappingRoutesFileList"),
    DEFAULT_CONTENT_TYPE_RESPONSE("defaultContentTypeResponse");

    public final String path;
    private static final String PACKAGE = "com.uaihebert.uaimockserver.";

    private RootConstants(final String path) {
        this.path = RootConstants.PACKAGE + path;
    }
}