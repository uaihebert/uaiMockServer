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

package com.uaihebert.uaimockserver.log.backend;

import com.uaihebert.uaimockserver.log.gui.UaiWebSocketLogManager;

/**
 * Project Log interface.
 * The implementation used will be hidden of the project behind this interface
 */
public final class Log {

    private static LogWriter logWriterInstance;

    private Log() {
    }

    /**
     * This instance can only be set by the builder that will only the available in the same package.
     *
     * @param log the instance that will be used
     */
    static void setInstance(final LogWriter log) {
        logWriterInstance = log;
    }

    /**
     * Wil log a text with the INFO level.
     */
    public static void info(final String text) {
        logWriterInstance.info(text);
        UaiWebSocketLogManager.addLogText("[INFO] " + text);
    }

    /**
     * Wil log a text with the INFO level. The text with be formatted using the String.format(...)
     */
    public static void infoFormatted(final String text, final Object... parameterArray) {
        final String formattedText = String.format(text, parameterArray);

        logWriterInstance.info(formattedText);

        UaiWebSocketLogManager.addLogText("[INFO] " + formattedText);
    }

    /**
     * Wil log a text with the WARN level. The text with be formatted using the String.format(...)
     */
    public static void warnFormatted(final String text, final Object... parameterArray) {
        final String formattedText = String.format(text, parameterArray);

        logWriterInstance.warn(formattedText);

        UaiWebSocketLogManager.addLogText("[WARN] " + formattedText);
    }

    /**
     * Wil log a text with the WARN level.
     */
    public static void warnFormatted(final Exception exception) {
        logWriterInstance.warn(exception);

        UaiWebSocketLogManager.addLogText("[WARN] An exception just happened: " + exception.getMessage());
    }
}
