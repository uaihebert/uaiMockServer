package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static void writeUpdatesToFile() {
        final UaiMockServerConfig mainConfig = UaiMockServerContext.getInstance().uaiMockServerConfig;

        final String mainJson = JsonUtil.toJsonWithNoEscaping(mainConfig);

        try {
            writeInFile(mainConfig, mainJson);

            for (UaiMockServerConfig secondaryConfig : UaiMockServerContext.getInstance().secondaryMappingList) {
                final String secondaryJson = JsonUtil.toJsonWithNoEscaping(secondaryConfig);
                writeInFile(secondaryConfig, secondaryJson);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("There was a problem when writing in the config files: " + ex.getMessage(), ex);
        }
    }

    private static void writeInFile(final UaiMockServerConfig configFile, final String jsonConfigContent) throws IOException {
        createFileBackUp(configFile.getUaiFile().getFullPath());

        FileUtils.writeStringToFile(new File(configFile.getUaiFile().getFullPath()), jsonConfigContent);

        Log.infoFormatted("The updates has been written in the config file [%s]", configFile.getUaiFile().getFullPath());
    }

    private static void createFileBackUp(final String fullPath) throws IOException {
        final String formattedDate = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_sss").format(new Date());

        FileUtils.copyFile(new File(fullPath), new File(fullPath.replace(".json", "_" + formattedDate + ".back.json")));
    }

    public static ByteBuffer getFileAsByteBuffer(String bodyPath) {
        try {
            final File file = findFile(bodyPath);

            final InputStream inputStream = new FileInputStream(file);

            final byte[] bytes = IOUtils.toByteArray(inputStream);

            return ByteBuffer.wrap(bytes);
        } catch (IOException e) {
            throw new IllegalArgumentException("problem opening the file: " + bodyPath, e);
        }
    }
}