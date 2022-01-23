package com.mycompany.locationapi.apiclient;

import com.mycompany.locationapi.model.OfferingModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OfferingApiService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${offering.api.baseurl}")
    private String apiBaseUrl;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    public OfferingModel fetchOfferingDetail(Long offeringId){

        OfferingModel offeringModel = null;
        try {
            offeringModel = restTemplate.getForObject(apiBaseUrl + "/api/v1/offerings/" + offeringId, OfferingModel.class);
        }catch (Exception ex){
            logger.error("Error occured while connecting to offering api at {}", apiBaseUrl);
        }
        return offeringModel;
    }
}
