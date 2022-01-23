package com.mycompany.offeringapi.controller;

import com.mycompany.offeringapi.model.OfferingModel;
import com.mycompany.offeringapi.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Description: This controller is reponsible for crud operation of offerings
* Author: John Doe
 */
@RestController
@RequestMapping("/api/v1")
public class OfferingController {

    @Autowired
    private OfferingService offeringService;

    @GetMapping("/offerings")
    public ResponseEntity<List<OfferingModel>> getAllOfferings(){
        List<OfferingModel> offeringModelList = offeringService.getAllOfferings();
        return new ResponseEntity<List<OfferingModel>>(offeringModelList, HttpStatus.OK);
    }

    @GetMapping("/offerings/{id}")
    public ResponseEntity<OfferingModel> getAllOfferingById(@PathVariable("id") Long offeringId){
        OfferingModel offeringModel = offeringService.getAllOfferingById(offeringId);
        return new ResponseEntity<OfferingModel>(offeringModel, HttpStatus.OK);
    }

    @PostMapping("/offerings")
    public ResponseEntity<String> createOffering(@RequestBody OfferingModel offeringModel){

        ResponseEntity<String> responseEntity = null;
        boolean result = offeringService.createOffering(offeringModel);

        if (result){
            responseEntity = new ResponseEntity<>("Offering created", HttpStatus.CREATED);
        }else{
            responseEntity = new ResponseEntity<>("Offering could not be created", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
