package com.mycompany.offeringapi.converter;

import com.mycompany.offeringapi.entity.OfferingEntity;
import com.mycompany.offeringapi.model.OfferingModel;
import org.springframework.stereotype.Component;

@Component
public class OfferingConverter {

    public OfferingModel convertEntityToModel(OfferingEntity offeringEntity){
        OfferingModel offeringModel = new OfferingModel();
        offeringModel.setOfferingId(offeringEntity.getId());
        offeringModel.setDescription(offeringEntity.getDescription());
        offeringModel.setOfferingName(offeringEntity.getOfferingName());
        offeringModel.setEligibilityCriteria(offeringEntity.getEligibilityCriteria());
        return offeringModel;
    }

    public OfferingEntity convertModelToEntity(OfferingModel offeringModel){
        OfferingEntity offeringEntity = new OfferingEntity();
        offeringEntity.setDescription(offeringModel.getDescription());
        offeringEntity.setOfferingName(offeringModel.getOfferingName());
        offeringEntity.setEligibilityCriteria(offeringModel.getEligibilityCriteria());
        return offeringEntity;
    }
}
