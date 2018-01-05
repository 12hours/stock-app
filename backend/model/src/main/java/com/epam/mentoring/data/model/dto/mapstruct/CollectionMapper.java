package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.dto.view.CollectionView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.HashMap;

@Mapper
public interface CollectionMapper<T> {

    default CollectionView collectionToCollectionView(Collection<T> collection){
        return new CollectionView(collection, new HashMap<String, Object>(), new HashMap<String, Object>());
    }

}
