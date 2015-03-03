package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public final class FileUtil {
    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "We could not find the file: [%s]." +
            "We looked into the same folder of the jar and we could not find it. %n" +
            "Check if the is in the test/resources folder or in the same folder of the jar. %n" +
            "If you want you can use the full the file path.";

    private FileUtil() {
    }

    public static File findFile(final String configFile) {
        final File fileOnSameDirectoryOrInResources = new File(configFile);

        if (fileOnSameDirectoryOrInResources.exists()) {
            return fileOnSameDirectoryOrInResources;
        }

        final URL url = UaiMockServerConfig.class.getResource("/" + configFile);

        if (url == null) {
            throw new IllegalArgumentException(String.format(FILE_NOT_FOUND_EXCEPTION_MESSAGE, configFile));
        }

        return new File(url.getFile());
    }

    public static String getFileContent(final String fileName) {
        final File file = findFile(fileName);

        return getFileContent(file);
    }

    public static String getFileContent(final File file) {
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new IllegalArgumentException("could not read the config file", e);
        }
    }
}