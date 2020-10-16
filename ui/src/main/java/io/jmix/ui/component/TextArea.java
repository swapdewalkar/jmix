/*
 * Copyright 2019 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jmix.ui.component;

import org.springframework.core.ParameterizedTypeReference;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetTime;

/**
 * A text field that supports multi line editing.
 *
 * @param <V> type of value
 */
public interface TextArea<V> extends TextInputField<V>,
                                  HasDatatype<V>,
                                  TextInputField.MaxLengthLimited,
                                  TextInputField.CursorPositionSupported,
                                  TextInputField.TrimSupported,
                                  TextInputField.TextChangeNotifier,
                                  TextInputField.TextSelectionSupported,
                                  TextInputField.CaseConversionSupported,
                                  HasInputPrompt,
                                  HasConversionErrorMessage {

    String NAME = "textArea";

    ParameterizedTypeReference<TextArea<String>> TYPE_DEFAULT =
            new ParameterizedTypeReference<TextArea<String>>() {};
    ParameterizedTypeReference<TextArea<String>> TYPE_STRING =
            new ParameterizedTypeReference<TextArea<String>>() {};

    ParameterizedTypeReference<TextArea<Integer>> TYPE_INTEGER =
            new ParameterizedTypeReference<TextArea<Integer>>() {};
    ParameterizedTypeReference<TextArea<Long>> TYPE_LONG =
            new ParameterizedTypeReference<TextArea<Long>>() {};
    ParameterizedTypeReference<TextArea<Double>> TYPE_DOUBLE =
            new ParameterizedTypeReference<TextArea<Double>>() {};
    ParameterizedTypeReference<TextArea<BigDecimal>> TYPE_BIGDECIMAL =
            new ParameterizedTypeReference<TextArea<BigDecimal>>() {};

    ParameterizedTypeReference<TextArea<Date>> TYPE_DATE =
            new ParameterizedTypeReference<TextArea<Date>>() {};
    ParameterizedTypeReference<TextArea<java.util.Date>> TYPE_DATETIME =
            new ParameterizedTypeReference<TextArea<java.util.Date>>() {};
    ParameterizedTypeReference<TextArea<LocalDate>> TYPE_LOCALDATE =
            new ParameterizedTypeReference<TextArea<LocalDate>>() {};
    ParameterizedTypeReference<TextArea<LocalDateTime>> TYPE_LOCALDATETIME =
            new ParameterizedTypeReference<TextArea<LocalDateTime>>() {};
    ParameterizedTypeReference<TextArea<Time>> TYPE_TIME =
            new ParameterizedTypeReference<TextArea<Time>>() {};
    ParameterizedTypeReference<TextArea<OffsetTime>> TYPE_OFFSETTIME =
            new ParameterizedTypeReference<TextArea<OffsetTime>>() {};

    /**
     * @return the number of rows
     */
    int getRows();

    /**
     * Sets the number of rows in the text area.
     *
     * @param rows the number of rows
     */
    void setRows(int rows);

    /**
     * @return whether word wrapping is enabled or not
     */
    boolean isWordWrap();

    /**
     * Sets whether word wrapping is enabled or not.
     *
     * @param wordWrap wordWrap
     */
    void setWordWrap(boolean wordWrap);

    /**
     * Returns a string representation of the value.
     */
    String getRawValue();
}