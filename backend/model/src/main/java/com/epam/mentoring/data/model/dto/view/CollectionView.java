package com.epam.mentoring.data.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionView<T> {
    private Collection<T> items;
    private HashMap<String, Object> properties;
    private HashMap<String, Object> links;
}
