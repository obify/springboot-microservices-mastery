package com.mycompany.offeringapi.service.impl;

import com.mycompany.offeringapi.converter.OfferingConverter;
import com.mycompany.offeringapi.entity.OfferingEntity;
import com.mycompany.offeringapi.model.OfferingModel;
import com.mycompany.offeringapi.repository.OfferingRepository;
import com.mycompany.offeringapi.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferingServiceImpl implements OfferingService {

    @Autowired
    private OfferingRepository offeringRepository;
    @Autowired
    private OfferingConverter offeringConverter;

    @Override
    public boolean createOffering(OfferingModel offeringModel) {
        OfferingEntity offeringEntity = offeringConverter.convertModelToEntity(offeringModel);
        OfferingEntity newOffering = offeringRepository.save(offeringEntity);
        if(newOffering != null){
            return true;
        }
        return false;
    }

    @Override
    public List<OfferingModel> getAllOfferings() {

        List<OfferingModel> offeringModelList = new ArrayList<>();
        Iterable<OfferingEntity> offeringEntities = offeringRepository.findAll();

        for (OfferingEntity offeringEntity : offeringEntities){
         OfferingModel offeringModel = offeringConverter.convertEntityToModel(offeringEntity);
            offeringModelList.add(offeringModel);
        }
        return offeringModelList;
    }

    @Override
    public OfferingModel getAllOfferingById(Long id) {

        OfferingModel offeringModel = null;

        Optional<OfferingEntity> offeringEntityOpt = offeringRepository.findById(id);
        if(offeringEntityOpt.isPresent()){
            offeringModel = offeringConverter.convertEntityToModel(offeringEntityOpt.get());
        }

        return offeringModel;
    }
}
