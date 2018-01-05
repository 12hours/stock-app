package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.HashMap;

@Mapper
public interface CollectionMapper<T> {

    default CollectionDTO collectionToCollectionView(Collection<T> collection){
        return new CollectionDTO(collection, new HashMap<String, Object>(), new HashMap<String, Object>());
    }

}
