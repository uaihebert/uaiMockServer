package com.uaihebert.uaimockserver.factory;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;

import java.io.File;
import java.net.URL;

/**
 * This factory will create an instance of the TypeSafe Config class
 */
public final class TypeSafeConfigFactory {
    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "We could not find the file: [%s]." +
            "We looked into the same folder of the jar and we could not find it.\n" +
            "Check if the is in the test/resources folder or in the same folder of the jar.";

    private TypeSafeConfigFactory() {
    }

    public static Config loadConfiguration(final String fileName) {
        final File file = new File(fileName);

        if (file.exists()) {
            return ConfigFactory.parseFile(file);
        }

        final URL url = UaiMockServerConfig.class.getResource("/" + fileName);

        if (url == null) {
            throw new IllegalArgumentException(String.format(FILE_NOT_FOUND_EXCEPTION_MESSAGE, file));
        }

        final File urlFile = new File(url.getFile());

        return ConfigFactory.parseFile(urlFile);
    }
}