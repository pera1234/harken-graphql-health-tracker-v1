package com.harken.graphql.server.domain.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterItem {
    String columnField;
    String operatorValue;
    String value;
}
