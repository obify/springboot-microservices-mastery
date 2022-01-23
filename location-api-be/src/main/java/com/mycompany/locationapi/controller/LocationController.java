package com.mycompany.locationapi.controller;

import com.mycompany.locationapi.model.LocationDetailModel;
import com.mycompany.locationapi.model.LocationModel;
import com.mycompany.locationapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations/users/{userId}")
    public ResponseEntity<List<LocationModel>> getAllLocations(@PathVariable("userId") Long userId){

        ResponseEntity<List<LocationModel>> responseEntity = null;
        List<LocationModel> locationModels = locationService.getAllLocations(userId);

        responseEntity =  new ResponseEntity<>(locationModels, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/locations/{locationId}/users/{userId}")
    public ResponseEntity<LocationDetailModel> getLocationDetail(@PathVariable("locationId") Long locationId, @PathVariable("userId") Long userId){

        ResponseEntity<LocationDetailModel> responseEntity = null;
        LocationDetailModel locationDetailModel = locationService.getLocationDetail(locationId ,userId);
        if(locationDetailModel != null) {
            responseEntity = new ResponseEntity<>(locationDetailModel, HttpStatus.OK);
        }else{
            responseEntity = new ResponseEntity<>(locationDetailModel, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping("/locations/users")
    public ResponseEntity<String> createLocation(@RequestBody LocationModel locationModel){

        ResponseEntity<String> responseEntity = null;
        boolean result = locationService.createLocation(locationModel);

        if (result){
            responseEntity = new ResponseEntity<>("Location created", HttpStatus.CREATED);
        }else{
            responseEntity = new ResponseEntity<>("Location could not be created", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @PutMapping("/locations/{locationId}/users/{userId}")
    public void updateLocation(@PathVariable("locationId") Long locationId,
                               @RequestBody LocationModel locationModel, @PathVariable("userId") Long userId){

    }

    @DeleteMapping("/locations/{locationId}/users/{userId}")
    public void deleteLocation(@PathVariable("locationId") Long locationId, @PathVariable("userId") Long userId){

    }
}
