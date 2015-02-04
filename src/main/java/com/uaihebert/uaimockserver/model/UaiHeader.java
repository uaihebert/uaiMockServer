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

import java.util.List;

/**
 * Class that will hold all the header data
 */
public class UaiHeader {

    public final String name;
    public final boolean usingWildCard;
    public final List<String> valueList;

    public UaiHeader(final String name, final boolean usingWildCard, final List<String> valueList) {
        this.name = name;
        this.usingWildCard = usingWildCard;
        this.valueList = valueList;
    }
}