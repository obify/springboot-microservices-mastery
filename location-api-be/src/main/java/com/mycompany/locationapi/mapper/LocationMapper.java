package com.mycompany.locationapi.mapper;

import com.mycompany.locationapi.entity.LocationEntity;
import com.mycompany.locationapi.model.LocationModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationModel convertEntityToModel(LocationEntity locationEntity);
    LocationEntity convertModelToEntity(LocationModel locationModel);
}
