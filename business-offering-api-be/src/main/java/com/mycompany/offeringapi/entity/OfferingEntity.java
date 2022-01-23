package com.mycompany.offeringapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "OFFERING_TABLE")
public class OfferingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String offeringName;
    private String description;
    private String eligibilityCriteria;
}
