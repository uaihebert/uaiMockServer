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
import com.uaihebert.uaimockserver.constants.QueryParamConstants;
import com.uaihebert.uaimockserver.constants.RequestConstants;
import com.uaihebert.uaimockserver.constants.ValidatorConstants;
import com.uaihebert.uaimockserver.dto.model.UaiQueryParamDTO;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.util.ConfigKeyUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This factory will create an instance of the UaiQueryParam.java
 */
public final class UaiQueryParamFactory {
    private UaiQueryParamFactory() {
    }

    public static List<UaiQueryParam> create(final Config config) {
        final List<? extends Config> queryParamConfig = ConfigKeyUtil.getConfigListSilently(RequestConstants.REQUIRED_QUERY_PARAM_LIST.path, config);
        final List<UaiQueryParam> uaiQueryParamList = new ArrayList<UaiQueryParam>();

        for (Config headerConfig : queryParamConfig) {
            final UaiQueryParam uaiQueryParam = createQueryParam(headerConfig);
            uaiQueryParamList.add(uaiQueryParam);
        }

        return uaiQueryParamList;
    }

    private static UaiQueryParam createQueryParam(final Config config) {
        final String name = ConfigKeyUtil.getStringSilently(QueryParamConstants.NAME.path, config);
        final List<String> valueList = ConfigKeyUtil.getStringListSilently(QueryParamConstants.VALUE_LIST.path, config);

        final boolean usingWildCard = valueList.contains(ValidatorConstants.VALID_WILD_CARD.text);

        return new UaiQueryParam(name, usingWildCard, valueList);
    }

    public static List<UaiQueryParam> create(final List<UaiQueryParamDTO> requiredQueryParamList) {
        if (requiredQueryParamList == null || requiredQueryParamList.isEmpty()) {
            return Collections.emptyList();
        }

        final List<UaiQueryParam> uaiQueryParamList = new ArrayList<UaiQueryParam>();

        for (UaiQueryParamDTO uaiQueryParamDTO : requiredQueryParamList) {
            final boolean usingWildCard = uaiQueryParamDTO.getValueList().contains(ValidatorConstants.VALID_WILD_CARD.text);

            final UaiQueryParam uaiQueryParam = new UaiQueryParam(uaiQueryParamDTO.getName(), usingWildCard, uaiQueryParamDTO.getValueList());
            uaiQueryParamList.add(uaiQueryParam);
        }

        return uaiQueryParamList;
    }
}