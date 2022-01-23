package com.mycompany.locationapi.service;

import com.mycompany.locationapi.model.LocationDetailModel;
import com.mycompany.locationapi.model.LocationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface LocationService {

    public List<LocationModel> getAllLocations(Long userId);

    public boolean createLocation(LocationModel locationModel);

    public LocationModel updateLocation(Long locationId, LocationModel locationModel, Long userId);

    public boolean deleteLocation(Long locationId, Long userId);

    public LocationDetailModel getLocationDetail(Long locationId,Long userId);
}