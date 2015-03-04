package com.uaihebert.uaimockserver.util;

import com.google.gson.GsonBuilder;
import com.uaihebert.uaimockserver.log.Log;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiMockServerContext;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public final class FileUtil {
    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "We could not find the file: [%s]." +
            "We looked into the same folder of the jar and we could not find it. %n" +
            "Check if the is in the test/resources folder or in the same folder of the jar. %n" +
            "If you want you can use the full the file path.";

    private static final GsonBuilder gsonBuilder = new GsonBuilder()
                                                        .setPrettyPrinting()
                                                        .disableHtmlEscaping()
                                                        .registerTypeHierarchyAdapter(Collection.class, new GsonCollectionAdapter())
                                                        .setExclusionStrategies(new GsonAttributesToIgnore());

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

    // todo display error message if the file could not be written
    // todo hide the route.id from the .json (?)
    // todo hide headerQueryParam using wildcard
    // todo when there is only one route, no route is received when update
    public static void writeUpdatesToFile() {
        final UaiMockServerConfig mainConfig = UaiMockServerContext.INSTANCE.uaiMockServerConfig;

        final String json = gsonBuilder.create().toJson(mainConfig);

        try {
            FileUtils.writeStringToFile(new File("/home/uaihebert/Desktop/test.txt"), json);
            Log.info("The updates has been written in the config file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}