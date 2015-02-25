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

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.constants.HeaderConstants;
import com.uaihebert.uaimockserver.constants.ValidatorConstants;
import com.uaihebert.uaimockserver.dto.model.UaiHeaderDTO;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.util.ConfigKeyUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This factory will create an instance of the UaiHeader.java
 */
public final class UaiHeaderFactory {
    private UaiHeaderFactory() {
    }

    public static List<UaiHeader> create(final Config config, final String headerListKey) {
        final List<? extends Config> headerConfigList = ConfigKeyUtil.getConfigListSilently(headerListKey, config);

        final List<UaiHeader> uaiHeaderList = new ArrayList<UaiHeader>();

        for (Config headerConfig : headerConfigList) {
            final UaiHeader uaiHeader = createHeader(headerConfig);
            uaiHeaderList.add(uaiHeader);
        }

        return uaiHeaderList;
    }

    private static UaiHeader createHeader(final Config config) {
        final String name = ConfigKeyUtil.getStringSilently(HeaderConstants.NAME.path, config);
        final List<String> valueList = ConfigKeyUtil.getStringListSilently(HeaderConstants.VALUE_LIST.path, config);

        final boolean usingWildCard = valueList.contains(ValidatorConstants.VALID_WILD_CARD.text);

        return new UaiHeader(name, usingWildCard, valueList);
    }

    public static List<UaiHeader> create(final List<UaiHeaderDTO> requiredHeaderList) {
        if (requiredHeaderList == null || requiredHeaderList.isEmpty()) {
            return Collections.emptyList();
        }

        final List<UaiHeader> uaiHeaderList = new ArrayList<UaiHeader>();

        for (UaiHeaderDTO uaiHeaderDTO : requiredHeaderList) {
            final boolean usingWildCard = uaiHeaderDTO.getValueList().contains(ValidatorConstants.VALID_WILD_CARD.text);

            final UaiHeader uaiHeader = new UaiHeader(uaiHeaderDTO.getName(), usingWildCard, uaiHeaderDTO.getValueList());
            uaiHeaderList.add(uaiHeader);
        }

        return uaiHeaderList;
    }
}