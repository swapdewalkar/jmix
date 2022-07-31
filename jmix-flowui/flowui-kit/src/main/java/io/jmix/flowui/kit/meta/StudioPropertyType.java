/*
 * Copyright 2022 Haulmont.
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

package io.jmix.flowui.kit.meta;

/**
 * Type of {@link StudioProperty}.
 */
public enum StudioPropertyType {
    /**
     * Identifier of an action defined in view.
     */
    ACTION_REF,

    /**
     * Big decimal type.
     */
    BIG_DECIMAL,

    /**
     * Boolean type.
     */
    BOOLEAN,

    /**
     * Identifier of a component. Must be a valid Java identifier.
     */
    COMPONENT_ID,

    /**
     * Identifier of a component defined in view.
     */
    COMPONENT_REF,

    /**
     * Double type.
     */
    DOUBLE,

    /**
     * Value from a strict list of property options.
     */
    ENUMERATION,

    /**
     * Float type.
     */
    FLOAT,

    /**
     * Icon type.
     * @see com.vaadin.flow.component.icon.VaadinIcon
     */
    ICON,

    /**
     * Integer type.
     */
    INTEGER,

    /**
     * String value or message key with msg:// prefix.
     */
    LOCALIZED_STRING,

    /**
     * Long type.
     */
    LONG,

    /**
     * Arbitrary value represented by string with a list of possible options.
     * Similar to {@link StudioPropertyType#ENUMERATION}, but allows text input.
     */
    OPTIONS,

    /**
     * Size value, e.g. width or height
     */
    SIZE,

    /**
     * Keyboard shortcut.
     */
    SHORTCUT,

    /**
     * String type.
     */
    STRING,

    /**
     * List of values separated with comma symbol.
     */
    VALUES_LIST
}