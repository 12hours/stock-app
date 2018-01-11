package com.epam.mentoring.data.model.dto;

import lombok.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class CollectionDTO<T> {
    private Collection<T> items;
    private Map<String, Object> properties;
    private Map<String, Object> links;

    public CollectionDTO() {
    }

    public CollectionDTO(Collection<T> items) {
        this.items = items;
        this.properties = new HashMap<>();
        this.links = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionDTO<?> that = (CollectionDTO<?>) o;
        return items.containsAll(that.items) &&
                Objects.equals(properties, that.properties) &&
                Objects.equals(links, that.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, properties, links);
    }
}
