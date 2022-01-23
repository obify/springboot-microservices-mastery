package com.mycompany.offeringapi.service;

import com.mycompany.offeringapi.model.OfferingModel;

import java.util.List;

public interface OfferingService {

    public boolean createOffering(OfferingModel offeringModel);
    public List<OfferingModel> getAllOfferings();
    public OfferingModel getAllOfferingById(Long id);
}
