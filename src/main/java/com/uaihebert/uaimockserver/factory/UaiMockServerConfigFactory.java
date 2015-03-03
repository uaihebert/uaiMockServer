package com.uaihebert.uaimockserver.factory;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.model.UaiFile;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class UaiMockServerConfigFactory {
    private UaiMockServerConfigFactory() {
    }

    public static UaiMockServerConfig create(final String fileName) {
        final String fileContent = FileUtil.getFileContent(fileName);
        final UaiMockServerConfig uaiMockServerConfig = new Gson().fromJson(fileContent, UaiMockServerConfig.class);
        uaiMockServerConfig.postConstruct();

        final File file = FileUtil.findFile(fileName);
        uaiMockServerConfig.setConfigFile(new UaiFile(file.getName(), file.getAbsolutePath()));

        return uaiMockServerConfig;
    }

    public static List<UaiMockServerConfig> create(final List<String> mappingRoutesFileList) {
        final List<UaiMockServerConfig> secondaryConfigList = new ArrayList<UaiMockServerConfig>();

        for (String fileName : mappingRoutesFileList) {
            final UaiMockServerConfig secondaryConfiguration = create(fileName);
            secondaryConfigList.add(secondaryConfiguration);
        }


        return secondaryConfigList;
    }
}