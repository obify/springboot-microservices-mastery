package com.mycompany.offeringapi.repository;

import com.mycompany.offeringapi.entity.OfferingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferingRepository extends CrudRepository<OfferingEntity, Long> {
}
