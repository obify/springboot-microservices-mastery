package com.mycompany.locationmanagementapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity(name = "USER_ENTITY_TABLE")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "FULL_NAME")
    private String fullName;
    private String mobileNumber;
    private String email;
    private String password;
}
