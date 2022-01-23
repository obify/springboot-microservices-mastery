package com.mycompany.locationapi.repository;

import com.mycompany.locationapi.entity.LocationOfferingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationOfferingRepository extends CrudRepository<LocationOfferingEntity, Long> {

    public List<LocationOfferingEntity> findByLocationId(Long locationId);
}
