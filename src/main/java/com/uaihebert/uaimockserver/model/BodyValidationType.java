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

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade.RequestAnalysisResult;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.util.StringUtils;
import com.uaihebert.uaimockserver.validator.body.UaiJSONComparator;
import com.uaihebert.uaimockserver.validator.body.UaiJSONCompareWrapper;
import com.uaihebert.uaimockserver.validator.body.UaiJsonFieldFailureLogger;
import com.uaihebert.uaimockserver.validator.body.XmlUnitWrapper;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

import java.util.regex.Pattern;

@SuppressWarnings("DeclarationOrder")
public enum BodyValidationType {
    VALIDATE_IF_PRESENT_ONLY {
        @Override
        public void validate(final String body, final UaiRequest request, final RequestAnalysisResult result) {
            if (StringUtils.isNotBlank(body)) {
                return;
            }

            Log.warnFormatted(BODY_VALIDATOR_ERROR_MESSAGE, request.method, request.path);
            result.abortTheRequest();
        }
    },
    RAW_TEXT {
        @Override
        public void validate(final String body, final UaiRequest request, final RequestAnalysisResult result) {
            if (request.body.equals(body)) {
                return;
            }

            Log.warnFormatted(WRONG_RAW_TEXT_BODY, body, request.body);
            result.abortTheRequest();
        }
    },
    JSON_VALIDATE_DEFINED_BODY_AGAINST_ALL_ATTRIBUTES_RECEIVED {
        @Override
        public void validate(final String body, final UaiRequest request, final RequestAnalysisResult result) {
            validateJson(body, request, result, STRICT_COMPARATOR, JSON_BODY_STRICT_ERROR_MESSAGE);
        }
    },
    JSON_VALIDATE_ONLY_DEFINED_ATTRIBUTES_IN_BODY {
        @Override
        public void validate(final String body, final UaiRequest request, final RequestAnalysisResult result) {
            validateJson(body, request, result, LENIENT_COMPARATOR, JSON_BODY_LENIENT_ERROR_MESSAGE);
        }
    },
    XML_BODY_WITH_STRICT_ATTRIBUTE_ORDER {
        @Override
        public void validate(final String body, final UaiRequest request, final RequestAnalysisResult result) {
            VALIDATE_IF_PRESENT_ONLY.validate(body, request, result);

            if (result.isAbortTheRequest()) {
                return;
            }

            if (XmlUnitWrapper.isIdentical(request.body, body)) {
                return;
            }

            Log.warnFormatted(WRONG_XML_BODY_WITH_STRICT_ATTRIBUTE_ORDER, body, request.body);

            result.abortTheRequest();
        }
    },
    XML_BODY_WITHOUT_STRICT_ATTRIBUTE_ORDER {
        @Override
        public void validate(final String body, final UaiRequest request, final RequestAnalysisResult result) {
            VALIDATE_IF_PRESENT_ONLY.validate(body, request, result);

            if (result.isAbortTheRequest()) {
                return;
            }

            if (XmlUnitWrapper.isSimilar(request.body, body)) {
                return;
            }

            Log.warnFormatted(WRONG_XML_BODY_WITHOUT_STRICT_ATTRIBUTE_ORDER, body, request.body);

            result.abortTheRequest();
        }
    },
    BY_REGEX {
        @Override
        public void validate(final String body, final UaiRequest request, final RequestAnalysisResult result) {
            VALIDATE_IF_PRESENT_ONLY.validate(body, request, result);

            if (result.isAbortTheRequest()) {
                return;
            }

            final String delimiter = "\n";
            final String[] textPatterns = request.body.split(delimiter);

            for (String textPattern : textPatterns) {
                final Pattern pattern = Pattern.compile(textPattern);
                if (!pattern.matcher(body).find()) {
                    Log.warnFormatted(UNMATCHED_REGEX, body, textPattern);
                    result.abortTheRequest();
                }
            }

        }
    };

    private static void validateJson(final String body,
                              final UaiRequest request,
                              final RequestAnalysisResult result,
                              final JSONComparator comparator,
                              final String errorMessage) {
        VALIDATE_IF_PRESENT_ONLY.validate(body, request, result);

        if (result.isAbortTheRequest()) {
            return;
        }

        final JSONCompareResult jsonResult = UaiJSONCompareWrapper.compareJSON(request.body, body, comparator);

        if (!jsonResult.failed()) {
            return;
        }

        UaiJsonFieldFailureLogger.logFailure(errorMessage, jsonResult);

        result.abortTheRequest();
    }

    private static final JSONComparator STRICT_COMPARATOR = new UaiJSONComparator(JSONCompareMode.STRICT);
    private static final JSONComparator LENIENT_COMPARATOR = new UaiJSONComparator(JSONCompareMode.LENIENT);

    @SuppressWarnings("LineLength")
    private static final String WRONG_RAW_TEXT_BODY = "Using the RAW_TEXT validation we received a body with the following text in the body [%s], but the required body is [%s]";
    @SuppressWarnings("LineLength")
    private static final String BODY_VALIDATOR_ERROR_MESSAGE = "%nThe Route [%s - %s] was defined with the body as mandatory. Send a body in your request or set the bodyRequired to false. %n";
    @SuppressWarnings("LineLength")
    private static final String JSON_BODY_STRICT_ERROR_MESSAGE = "%nUsing the JSON_VALIDATE_DEFINED_BODY_AGAINST_ALL_ATTRIBUTES_RECEIVED validation we found an error with the attribute [%s]. %nWe received [%s] but what we detected was ---> [%s] %n";
    @SuppressWarnings("LineLength")
    private static final String JSON_BODY_LENIENT_ERROR_MESSAGE = "%nUsing the JSON_VALIDATE_ONLY_DEFINED_ATTRIBUTES_IN_BODY validation we found an error with the attribute [%s]. %nWe was expecting [%s] but what we detected was ---> [%s] %n";
    @SuppressWarnings("LineLength")
    private static final String WRONG_XML_BODY_WITH_STRICT_ATTRIBUTE_ORDER = "%nUsing the WRONG_XML_BODY_WITH_STRICT_ATTRIBUTE_ORDER validation we found and error with the XML that we received [%s]; the received XML is not equal to the expected body [%s]. Check the values and the attribute order. %n";
    @SuppressWarnings("LineLength")
    private static final String WRONG_XML_BODY_WITHOUT_STRICT_ATTRIBUTE_ORDER = "%nUsing the WRONG_XML_BODY_WITHOUT_STRICT_ATTRIBUTE_ORDER validation we found and error with the XML that we received [%s]; the received XML is not equal to the expected body [%s]. Check the values and the attribute order. %n";
    @SuppressWarnings("LineLength")
    private static final String UNMATCHED_REGEX = "%nUsing the BY_REGEX validation we found and error with the body that we received [%s]; the received body does not match one of the regular expressions [%s]. %n";

    public abstract void validate(String body, UaiRequest request, RequestAnalysisResult result);
}
