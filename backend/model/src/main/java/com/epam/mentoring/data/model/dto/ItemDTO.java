package com.epam.mentoring.data.model.dto;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
public class ItemDTO<T> {
    private T item;
    private HashMap<String, Object> links;

    public ItemDTO(T item) {
        this.item = item;
        this.links = new HashMap<>();
    }
}
