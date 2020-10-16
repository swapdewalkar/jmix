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

import javax.annotation.Nullable;
import java.math.BigDecimal;

/**
 * The CurrencyField component is intended for displaying currency values.
 */
public interface CurrencyField<V extends Number> extends Field<V>, HasDatatype<V>, HasConversionErrorMessage, Buffered,
                                                         Component.Focusable {

    String NAME = "currencyField";

    ParameterizedTypeReference<CurrencyField<BigDecimal>> TYPE_DEFAULT =
            new ParameterizedTypeReference<CurrencyField<BigDecimal>>() {};

    ParameterizedTypeReference<CurrencyField<BigDecimal>> TYPE_BIGDECIMAL =
            new ParameterizedTypeReference<CurrencyField<BigDecimal>>() {};
    ParameterizedTypeReference<CurrencyField<Integer>> TYPE_INTEGER =
            new ParameterizedTypeReference<CurrencyField<Integer>>() {};
    ParameterizedTypeReference<CurrencyField<Long>> TYPE_LONG =
            new ParameterizedTypeReference<CurrencyField<Long>>() {};
    ParameterizedTypeReference<CurrencyField<Double>> TYPE_DOUBLE =
            new ParameterizedTypeReference<CurrencyField<Double>>() {};

    /**
     * Sets the given <code>currency</code> to the field. Currency label will be displayed next to the text input
     * component if the <code>showCurrency</code> option is enabled.
     *
     * Recommended max currency length - 3 character.
     *
     * @param currency currency ($, EUR, etc)
     */
    void setCurrency(@Nullable String currency);

    /**
     * @return current currency
     */
    @Nullable
    String getCurrency();

    /**
     * Enables or disables currency label displaying.
     */
    void setShowCurrencyLabel(boolean showCurrencyLabel);

    /**
     * @return true if currency label is displayed or false otherwise
     */
    boolean getShowCurrencyLabel();

    /**
     * Sets where the currency label will be located: to the left or to the right from the text input component.
     *
     * @param currencyLabelPosition not-null {@link CurrencyLabelPosition} value
     */
    void setCurrencyLabelPosition(CurrencyLabelPosition currencyLabelPosition);

    /**
     * @return where the currency label is located
     */
    CurrencyLabelPosition getCurrencyLabelPosition();

    /**
     * Defines where the currency label is located.
     */
    enum CurrencyLabelPosition {
        /**
         * To the left from the text input component.
         */
        LEFT,
        /**
         * To the right from the text input component.
         */
        RIGHT
    }
}