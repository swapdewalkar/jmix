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

import io.jmix.core.metamodel.model.MetaPropertyPath;
import io.jmix.ui.component.data.GroupInfo;
import org.springframework.core.ParameterizedTypeReference;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Table UI component that supports grouping of rows by property values.
 *
 * @param <E> row item type
 */
public interface GroupTable<E> extends Table<E> {

    String NAME = "groupTable";

    static <T> ParameterizedTypeReference<GroupTable<T>> of(Class<T> itemClass) {
        return new ParameterizedTypeReference<GroupTable<T>>() {};
    }

    /**
     * Performs grouping by the given {@code properties}.
     *
     * @param properties an array of {@link MetaPropertyPath}
     */
    void groupBy(Object[] properties);

    /**
     * Performs grouping by the given ids of table columns.
     *
     * @param columnIds column ids
     */
    void groupByColumns(String... columnIds);

    /**
     * Resets grouping by the given ids of table columns.
     *
     * @param columnIds column ids
     */
    void ungroupByColumns(String... columnIds);

    /**
     * Resets grouping at all.
     */
    void ungroup();

    GroupCellValueFormatter<E> getGroupCellValueFormatter();

    void setGroupCellValueFormatter(GroupCellValueFormatter<E> formatter);

    void expandAll();

    void expand(GroupInfo groupId);

    /**
     * Expand all groups for specified item.
     */
    void expandPath(Object item);

    void collapseAll();

    void collapse(GroupInfo groupId);

    boolean isExpanded(GroupInfo groupId);

    boolean isFixedGrouping();

    void setFixedGrouping(boolean fixedGrouping);

    /**
     * @return true if GroupTable shows items count for group
     */
    boolean isShowItemsCountForGroup();

    /**
     * Show or hide items count for GroupTable groups. <br>
     * Default value is true.
     */
    void setShowItemsCountForGroup(boolean showItemsCountForGroup);

    /**
     * Returns a map with aggregation results for the given group info instance,
     * where keys are table column ids and values are aggregation value.
     *
     * @param info the group info instance
     * @return aggregation results for the given group info instance
     */
    Map<Object, Object> getAggregationResults(GroupInfo info);

    /**
     * Allows to define different styles for table cells.
     */
    interface GroupStyleProvider<E> extends StyleProvider<E> {
        /**
         * Called by {@link GroupTable} to get a style for group row.
         *
         * @param info an group represented by the current row
         * @return style name or null to apply the default
         */
        @Nullable
        String getStyleName(GroupInfo info);
    }

    @FunctionalInterface
    interface GroupCellValueFormatter<E> {
        String format(GroupCellContext<E> context);
    }

    class GroupCellContext<E> {
        private GroupInfo groupInfo;
        private Object value;
        private String formattedValue;
        private List<E> groupItems;

        public GroupCellContext(GroupInfo groupInfo, Object value, String formattedValue, List<E> groupItems) {
            this.groupInfo = groupInfo;
            this.value = value;
            this.formattedValue = formattedValue;
            this.groupItems = groupItems;
        }

        public GroupInfo getGroupInfo() {
            return groupInfo;
        }

        public Object getValue() {
            return value;
        }

        public List<E> getGroupItems() {
            return groupItems;
        }

        public String getFormattedValue() {
            return formattedValue;
        }
    }

    /**
     * Object that contains information about group aggregation distribution.
     *
     * @param <V> entity type
     */
    class GroupAggregationDistributionContext<V> extends AggregationDistributionContext<V> {

        protected GroupInfo groupInfo;

        public GroupAggregationDistributionContext(Column column, Object value, Collection<V> scope,
                                                   @Nullable GroupInfo groupInfo, boolean isTotalAggregation) {
            super(column, value, scope, isTotalAggregation);

            this.groupInfo = groupInfo;
        }

        @Nullable
        public GroupInfo getGroupInfo() {
            return groupInfo;
        }

        @Nullable
        public String getGroupPropertyPath() {
            return groupInfo != null
                    ? groupInfo.getProperty().toString()
                    : null;
        }
    }
}
