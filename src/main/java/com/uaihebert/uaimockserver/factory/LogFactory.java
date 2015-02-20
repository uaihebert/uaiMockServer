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

import com.uaihebert.uaimockserver.log.ActivatedLog;
import com.uaihebert.uaimockserver.log.DeactivatedLog;
import com.uaihebert.uaimockserver.log.Log;
import com.uaihebert.uaimockserver.model.UaiBasicServerConfiguration;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;

/**
 * This factory will create an instance of the Log
 *
 * If the log is deactivated, a class will be create with no actions
 *
 * It is not necessary to do if (log.isActive) around the code
 */
public final class LogFactory {
    private LogFactory() {
    }

    public static Log create(final UaiBasicServerConfiguration basicServerConfiguration) {
        if (!basicServerConfiguration.fileLog && !basicServerConfiguration.consoleLog) {
            return new DeactivatedLog();
        }

        final Configurator logConfigurator = Configurator.defaultConfig();
        logConfigurator.removeAllWriters();

        if (basicServerConfiguration.fileLog) {
            logConfigurator.addWriter(new FileWriter("uaiMockServer.log"));
        }

        if (basicServerConfiguration.consoleLog) {
            logConfigurator.addWriter(new ConsoleWriter());
        }

        logConfigurator.formatPattern("[uaiMockServer] {date:yyyy-MM-dd HH:mm:ss} [{thread}] {level}: {message}");

        logConfigurator.activate();

        return new ActivatedLog();
    }
}