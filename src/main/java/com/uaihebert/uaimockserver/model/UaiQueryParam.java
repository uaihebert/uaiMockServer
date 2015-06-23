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
package com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.constants.ValidatorConstants;

import java.util.Collections;
import java.util.List;

/**
 * Class that will hold all the query param data
 */
public class UaiQueryParam {
    private String name;

    private boolean usingWildCard;

    private List<String> valueList;

    public UaiQueryParam(final String name, final List<String> valueList) {
        this.name = name;
        this.usingWildCard = valueList.contains(ValidatorConstants.VALID_WILD_CARD.text);
        this.valueList = valueList;
    }

    public String getName() {
        return name;
    }

    public boolean isUsingWildCard() {
        return usingWildCard;
    }

    public void setUsingWildCard(final boolean usingWildCard) {
        this.usingWildCard = usingWildCard;
    }

    public List<String> getValueList() {
        if (valueList == null) {
            valueList = Collections.emptyList();
        }

        return valueList;
    }
}