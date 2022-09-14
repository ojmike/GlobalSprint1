package com.globalsprint.globalsprint1.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteUserRequest {

    @NotNull(message = "User id cannot be empty")
    private long userId;

    @NotBlank(message = "Email address cannot be empty")
    private String emailAddress;
}
