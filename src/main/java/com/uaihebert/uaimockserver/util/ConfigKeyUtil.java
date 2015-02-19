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

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * This class will help getting the configurations when it can be null.
 * The TypeSafe#Config class will raise exception is a config is not found.
 */
public final class ConfigKeyUtil {
    private ConfigKeyUtil() {
    }

    public static String getStringSilently(final String key, final Config config) {
        try {
            return config.getString(key);
        } catch (final ConfigException ex) {
            return StringUtils.EMPTY;
        }
    }

    public static List<String> getStringListSilently(final String key, final Config config) {
        try {
            return config.getStringList(key);
        } catch (final ConfigException ex) {
            return Collections.emptyList();
        }
    }

    public static boolean getBooleanSilently(final String key, final Config config) {
        final String configAsString = getStringSilently(key, config);

        if (StringUtils.isEmpty(configAsString)) {
            return false;
        }

        return Boolean.valueOf(configAsString);
    }

    public static long getLongSilently(final String key, final Config config) {
        final String configAsString = getStringSilently(key, config);

        if (StringUtils.isEmpty(configAsString)) {
            return 0L;
        }

        return Long.valueOf(configAsString);
    }

    public static List<? extends Config> getConfigListSilently(final String key, final Config config) {
        try {
            return config.getConfigList(key);
        } catch (final ConfigException ex) {
            return Collections.emptyList();
        }
    }
}