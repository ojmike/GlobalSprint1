package com.globalsprint.globalsprint1.payload.response;

import com.globalsprint.globalsprint1.model.User;
import com.globalsprint.globalsprint1.payload.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetUserResponse {

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate dateOfBirth;

    private String country;

    private Gender gender;

    public GetUserResponse(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.dateOfBirth = user.getDateOfBirth();
        this.country = user.getCountry();
        this.gender = user.getGender();
    }

}
