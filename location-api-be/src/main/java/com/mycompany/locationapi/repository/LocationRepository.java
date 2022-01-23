package com.mycompany.locationapi.repository;

import com.mycompany.locationapi.entity.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, Long> {
    public List<LocationEntity> findByUserId(Long userId);
    public LocationEntity findByIdAndUserId(Long locationId, Long userId);
}
