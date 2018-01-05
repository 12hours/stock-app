package com.epam.mentoring.data.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserView {
    private Integer id;
    private String name;
    private HashMap<String, Object> links;
}
