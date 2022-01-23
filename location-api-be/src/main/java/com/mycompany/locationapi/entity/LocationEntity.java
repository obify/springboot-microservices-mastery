package com.mycompany.locationapi.entity;

import com.mycompany.locationapi.constant.LocationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "LOCATION_ENTITY_TABLE")
@Data
@NoArgsConstructor
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOCATION_ID")
    private Long id;
    @Enumerated(EnumType.STRING)
    private LocationType locationType;
    private String plotNo;
    private String street;
    private String pincode;
    private String city;
    private String state;
    private String country;
    private Long userId;
    private Long openCloseTimeId;
    private String lat;
    private String lng;

}
