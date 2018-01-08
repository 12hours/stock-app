package com.epam.mentoring.data.model.dto;

import lombok.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CollectionDTO<T> {
    private Collection<T> items;
    private Map<String, Object> properties;
    private Map<String, Object> links;

    public CollectionDTO(Collection<T> items) {
        this.items = items;
        this.properties = new HashMap<>();
        this.links = new HashMap<>();
    }
}
