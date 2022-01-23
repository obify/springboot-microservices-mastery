package com.mycompany.offeringapi.model;

import lombok.Data;

@Data
public class OfferingModel {

    private Long offeringId;
    private String offeringName;
    private String description;
    private String eligibilityCriteria;
}
