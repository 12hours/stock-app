package com.epam.mentoring.data.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO<T> {
    private T item;
    private HashMap<String, Object> links;
}
