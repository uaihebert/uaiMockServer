package com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.model.UaiFile;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.util.FileUtil;
import com.uaihebert.uaimockserver.util.JsonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class UaiMockServerConfigFactory {
    private UaiMockServerConfigFactory() {
    }

    public static UaiMockServerConfig create(final String fileName) {
        final String fileContent = FileUtil.getFileContent(fileName);
        final UaiMockServerConfig uaiMockServerConfig = JsonUtil.fromJson(fileContent, UaiMockServerConfig.class);

        final File file = FileUtil.findFile(fileName);
        uaiMockServerConfig.setUaiFile(new UaiFile(file.getName(), file.getAbsolutePath()));

        uaiMockServerConfig.postConstruct();

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
