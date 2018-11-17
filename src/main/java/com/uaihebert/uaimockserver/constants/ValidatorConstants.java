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

package com.uaihebert.uaimockserver.constants;

/**
 * Constants to be used in the Validators classes.
 */
public enum ValidatorConstants {
    VALID_WILD_CARD("UAI_*");

    public final String text;

    ValidatorConstants(final String text) {
        this.text = text;
    }
}
