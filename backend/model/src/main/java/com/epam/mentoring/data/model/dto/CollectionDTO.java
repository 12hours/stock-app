package com.epam.mentoring.data.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDTO<T> {
    private Collection<T> items;
    private Map<String, Object> properties;
    private Map<String, Object> links;
}
