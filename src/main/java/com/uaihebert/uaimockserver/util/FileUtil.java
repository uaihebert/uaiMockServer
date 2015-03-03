package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiMockServerConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

        FileInputStream fileInputStream = null;
        final StringBuilder fileContent = new StringBuilder();

        try {
            fileInputStream = new FileInputStream(file);

            int content;
            while ((content = fileInputStream.read()) != -1) {
                fileContent.append((char) content);
            }

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("could not open the config file", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("could not read the config file", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new IllegalArgumentException("could not close the config file", e);
                }
            }
        }

        return fileContent.toString();
    }
}