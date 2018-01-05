package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.dto.ItemDTO;
import org.mapstruct.Mapper;

import java.util.HashMap;

@Mapper
public interface ItemMapper {

    default ItemDTO itemToItemDTO(Object item){
        return new ItemDTO(item, new HashMap<String, Object>());
    };

}
