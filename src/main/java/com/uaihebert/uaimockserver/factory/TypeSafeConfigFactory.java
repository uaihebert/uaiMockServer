package com.uaihebert.uaimockserver.factory;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

/**
 * This factory will create an instance of the TypeSafe Config class
 */
public final class TypeSafeConfigFactory {
    private TypeSafeConfigFactory() {
    }

    public static Config loadConfiguration(final File file) {
        return ConfigFactory.parseFile(file);
    }
}