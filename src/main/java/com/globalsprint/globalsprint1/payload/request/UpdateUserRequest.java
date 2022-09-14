package com.globalsprint.globalsprint1.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.globalsprint.globalsprint1.payload.enums.Gender;
import com.globalsprint.globalsprint1.utils.EmailValidator;
import com.globalsprint.globalsprint1.utils.PasswordValidator;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserRequest {

    @NotNull(message = "User id cannot be empty")
    private long userId;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @EmailValidator(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Transfer customer name is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "password is required")
    @PasswordValidator(message = "Please provide a valid password")
    private String password;
}
