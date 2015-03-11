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

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;

/**
 * This builder will create an instance of the Log
 * <p/>
 * If the log is deactivated, a class will be created that executes no actions
 * <p/>
 * It is not necessary to do if (log.isActive) around the code
 */
public final class LogBuilder {
    private LogBuilder() {
    }

    public static void createInstance() {
        final boolean fileLog = UaiMockServerContext.getInstance().uaiMockServerConfig.isFileLog();
        final boolean consoleLog = UaiMockServerContext.getInstance().uaiMockServerConfig.isConsoleLog();

        createInstance(fileLog, consoleLog);
    }

    public static void createInstance(boolean fileLog, boolean consoleLog) {
        if (!fileLog && !consoleLog) {
            Log.setInstance(new DeactivatedLog());
            return;
        }

        final Configurator logConfigurator = Configurator.defaultConfig();
        logConfigurator.removeAllWriters();

        if (fileLog) {
            logConfigurator.addWriter(new FileWriter("uaiMockServer.log"));
        }

        if (consoleLog) {
            logConfigurator.addWriter(new ConsoleWriter());
        }

        logConfigurator.formatPattern("[uaiMockServer] {date:yyyy-MM-dd HH:mm:ss} [{thread}] {level}: {message}");

        logConfigurator.activate();

        Log.setInstance(new ActivatedLog());
    }
}