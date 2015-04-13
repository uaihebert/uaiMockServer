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

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.util.StringUtils;
import com.uaihebert.uaimockserver.validator.body.UaiJSONComparator;
import com.uaihebert.uaimockserver.validator.body.UaiJSONCompareWrapper;
import com.uaihebert.uaimockserver.validator.body.UaiJsonFieldFailureLogger;
import com.uaihebert.uaimockserver.validator.body.XmlUnitWrapper;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

public enum BodyValidationType {
    VALIDATE_IF_PRESENT_ONLY {
        @Override
        public void validate(final String body, final UaiRequest uaiRequest, final RequestValidatorFacade.RequestAnalysisResult result) {
            if (StringUtils.isNotBlank(body)) {
                return;
            }

            Log.warnFormatted(BODY_VALIDATOR_ERROR_MESSAGE, uaiRequest.method, uaiRequest.path);
            result.abortTheRequest();
        }
    },
    RAW_TEXT {
        @Override
        public void validate(final String body, final UaiRequest uaiRequest, final RequestValidatorFacade.RequestAnalysisResult result) {
            if (uaiRequest.body.equals(body)) {
                return;
            }

            Log.warnFormatted(WRONG_RAW_TEXT_BODY, body, uaiRequest.body);
            result.abortTheRequest();
        }
    },
    JSON_BODY_WITH_STRICT_VALIDATION {
        @Override
        public void validate(final String body, final UaiRequest uaiRequest, final RequestValidatorFacade.RequestAnalysisResult result) {
            VALIDATE_IF_PRESENT_ONLY.validate(body, uaiRequest, result);

            if (result.isAbortTheRequest()) {
                return;
            }

            final JSONCompareResult jsonResult = UaiJSONCompareWrapper.compareJSON(uaiRequest.body, body, STRICT_COMPARATOR);

            if (!jsonResult.failed()) {
                return;
            }

            UaiJsonFieldFailureLogger.logFailure(JSON_BODY_STRICT_ERROR_MESSAGE, jsonResult);

            result.abortTheRequest();
        }
    },
    XML_BODY_WITH_STRICT_ATTRIBUTE_ORDER {
        @Override
        public void validate(final String body, final UaiRequest uaiRequest, final RequestValidatorFacade.RequestAnalysisResult result) {
            VALIDATE_IF_PRESENT_ONLY.validate(body, uaiRequest, result);

            if (result.isAbortTheRequest()) {
                return;
            }

            if (XmlUnitWrapper.isIdentical(uaiRequest.body, body)) {
                return;
            }

            Log.warnFormatted(WRONG_XML_BODY_WITH_STRICT_ATTRIBUTE_ORDER, body, uaiRequest.body);

            result.abortTheRequest();
        }
    },
    XML_BODY_WITHOUT_STRICT_ATTRIBUTE_ORDER {
        @Override
        public void validate(final String body, final UaiRequest uaiRequest, final RequestValidatorFacade.RequestAnalysisResult result) {
            VALIDATE_IF_PRESENT_ONLY.validate(body, uaiRequest, result);

            if (result.isAbortTheRequest()) {
                return;
            }

            if (XmlUnitWrapper.isSimilar(uaiRequest.body, body)) {
                return;
            }

            Log.warnFormatted(WRONG_XML_BODY_WITHOUT_STRICT_ATTRIBUTE_ORDER, body, uaiRequest.body);

            result.abortTheRequest();
        }
    };

    private static final JSONComparator STRICT_COMPARATOR = new UaiJSONComparator(JSONCompareMode.STRICT);

    private static final String WRONG_RAW_TEXT_BODY = "Using the RAW_TEXT validation we received a body with the following text in the body [%s], but the required body is [%s]";
    private static final String BODY_VALIDATOR_ERROR_MESSAGE = "%nThe Route [%s - %s] was defined with the body as mandatory. Send a body in your request or set the bodyRequired to false. %n";
    private static final String JSON_BODY_STRICT_ERROR_MESSAGE = "%nUsing the JSON_BODY_WITH_STRICT_VALIDATION validation we found an error with the attribute [%s]. %nWe was expecting [%s] but what we detected was ---> [%s] %n";
    private static final String WRONG_XML_BODY_WITH_STRICT_ATTRIBUTE_ORDER = "%nUsing the WRONG_XML_BODY_WITH_STRICT_ATTRIBUTE_ORDER validation we found and error with the XML that we received [%s]; the received XML is not equal to the expected body [%s]. Check the values and the attribute order. %n";
    private static final String WRONG_XML_BODY_WITHOUT_STRICT_ATTRIBUTE_ORDER = "%nUsing the WRONG_XML_BODY_WITHOUT_STRICT_ATTRIBUTE_ORDER validation we found and error with the XML that we received [%s]; the received XML is not equal to the expected body [%s]. Check the values and the attribute order. %n";

    public abstract void validate(final String body, final UaiRequest uaiRequest, final RequestValidatorFacade.RequestAnalysisResult result);
}