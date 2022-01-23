package com.mycompany.locationapi.service.impl;

import com.mycompany.locationapi.apiclient.GeocodeApiService;
import com.mycompany.locationapi.apiclient.GeocodeResponse;
import com.mycompany.locationapi.apiclient.GeocodeResponseWrapper;
import com.mycompany.locationapi.apiclient.OfferingApiService;
import com.mycompany.locationapi.converter.LocationConverter;
import com.mycompany.locationapi.entity.LocationEntity;
import com.mycompany.locationapi.entity.LocationOfferingEntity;
import com.mycompany.locationapi.mapper.LocationMapper;
import com.mycompany.locationapi.model.LocationDetailModel;
import com.mycompany.locationapi.model.LocationModel;
import com.mycompany.locationapi.model.OfferingModel;
import com.mycompany.locationapi.repository.LocationOfferingRepository;
import com.mycompany.locationapi.repository.LocationRepository;
import com.mycompany.locationapi.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/*
 * description: This class is responsible for the main business logic
 * author: John Doe
 */
@Service
public class LocationServiceImpl implements LocationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private GeocodeApiService geocodeApiService;
    @Autowired
    private OfferingApiService offeringApiService;

    @Autowired
    private LocationOfferingRepository locationOfferingRepository;

    @Autowired
    private LocationConverter locationConverter;

    /*
    * method name: getAllLocations
    * args: Long
    * return: List<LocationModel>
    * author: John Doe
    * description: This method gets all locations
     */
    @Override
    public List<LocationModel> getAllLocations(Long userId) {

        List<LocationEntity> locationEntities = locationRepository.findByUserId(userId);
        List<LocationModel> locationModels = new ArrayList<>();

        for(LocationEntity locationEntity : locationEntities){
          //LocationModel locationModel =  LocationMapper.INSTANCE.convertEntityToModel(locationEntity);
            LocationModel locationModel = locationConverter.convertLocationEntityToModel(locationEntity);
            locationModels.add(locationModel);
        }
        return locationModels;
    }

    /*
     * method name: createLocation
     * args: LocationModel, Long
     * return: boolean
     * author: John Doe
     * description: This method creates a new location
     */
    @Override
    public boolean createLocation(LocationModel locationModel) {

        //call to the external geocode api
        GeocodeResponse response = geocodeApiService.getCoordinates(locationModel);
        if(response != null){
            locationModel.setLng(response.getLongitude().toString());
            locationModel.setLat(response.getLatitude().toString());
        }
        LocationEntity locationEntity = locationConverter.convertLocationModelToEntity(locationModel);
        //LocationEntity locationEntity = LocationMapper.INSTANCE.convertModelToEntity(locationModel);
        LocationEntity newLocation = locationRepository.save(locationEntity);

        //save the mapping for location to its offerings
        LocationOfferingEntity locationOfferingEntity = null;
        for(Long offeringId :locationModel.getOfferingIdList()){
            locationOfferingEntity = new LocationOfferingEntity();
            locationOfferingEntity.setOfferingId(offeringId);
            locationOfferingEntity.setLocationId(newLocation.getId());
            locationOfferingRepository.save(locationOfferingEntity);
        }

        if(newLocation != null){
            return true;
        }
        return false;
    }

    @Override
    public LocationModel updateLocation(Long locationId, LocationModel locationModel, Long userId) {
        return null;
    }

    @Override
    public boolean deleteLocation(Long locationId, Long userId) {
        return false;
    }

    @Override
    public LocationDetailModel getLocationDetail(Long locationId, Long userId) {

        LocationDetailModel locationDetailModel = null;
        LocationEntity locationEntity = locationRepository.findByIdAndUserId(locationId, userId);

        if(null != locationEntity){

            locationDetailModel = new LocationDetailModel();
            locationDetailModel.setCity(locationEntity.getCity());
            locationDetailModel.setCountry(locationEntity.getCountry());
            locationDetailModel.setLat(locationEntity.getLat());
            locationDetailModel.setLng(locationEntity.getLng());
            locationDetailModel.setLocationId(locationEntity.getId());
            locationDetailModel.setPlotNo(locationEntity.getPlotNo());
            locationDetailModel.setStreet(locationEntity.getStreet());
            locationDetailModel.setState(locationEntity.getState());
            locationDetailModel.setUserId(locationEntity.getUserId());
            locationDetailModel.setPincode(locationEntity.getPincode());
            locationDetailModel.setLocationType(locationEntity.getLocationType());

            List<OfferingModel> offeringList = new ArrayList<>();
            List<LocationOfferingEntity> offeringIdList = locationOfferingRepository.findByLocationId(locationId);
            for(LocationOfferingEntity locationOffering :offeringIdList){
                //make call to offering api to fetch 1 offering detail
                OfferingModel offeringModel = offeringApiService.fetchOfferingDetail(locationOffering.getOfferingId());
                offeringList.add(offeringModel);
            }

            locationDetailModel.setOfferingList(offeringList);
        }
        return locationDetailModel;
    }

}
