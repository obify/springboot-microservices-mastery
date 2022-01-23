package com.mycompany.locationmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {

    @NotNull(message = "full name cannot be empty")
    private String fullName;

    @NotNull(message = "mobile number cannot be empty")
    @Size(min = 10, max = 13, message = "mobile number should be of 10-13 characters in length")
    private String mobileNumber;

    @NotNull(message = "email cannot be empty")
    @Email(message = "invalid email")
    private String email;

    @NotNull(message = "password cannot be empty")
    private String password;
}
