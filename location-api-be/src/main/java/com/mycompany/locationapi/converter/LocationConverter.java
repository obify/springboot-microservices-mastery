package com.mycompany.locationapi.converter;

import com.mycompany.locationapi.entity.LocationEntity;
import com.mycompany.locationapi.model.LocationModel;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter {

    public LocationEntity convertLocationModelToEntity(LocationModel locationModel){

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCity(locationModel.getCity());
        locationEntity.setCountry(locationModel.getCountry());
        locationEntity.setLat(locationModel.getLat());
        locationEntity.setLng(locationModel.getLng());
        locationEntity.setLocationType(locationModel.getLocationType());
        locationEntity.setPincode(locationModel.getPincode());
        locationEntity.setPlotNo(locationModel.getPlotNo());
        locationEntity.setState(locationModel.getState());
        locationEntity.setStreet(locationModel.getStreet());
        locationEntity.setUserId(locationModel.getUserId());

        return locationEntity;
    }

    public LocationModel convertLocationEntityToModel(LocationEntity locationEntity){

        LocationModel locationModel = new LocationModel();
        locationModel.setCity(locationEntity.getCity());
        locationModel.setCountry(locationEntity.getCountry());
        locationModel.setLat(locationEntity.getLat());
        locationModel.setLng(locationEntity.getLng());
        locationModel.setLocationType(locationEntity.getLocationType());
        locationModel.setPincode(locationEntity.getPincode());
        locationModel.setPlotNo(locationEntity.getPlotNo());
        locationModel.setState(locationEntity.getState());
        locationModel.setStreet(locationEntity.getStreet());
        locationModel.setUserId(locationModel.getUserId());
        locationModel.setLocationId(locationEntity.getId());

        return locationModel;
    }
}
