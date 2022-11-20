package com.globalsprint.globalsprint1.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStateRequest {
    private Long id;

    private String name;

    private String capital;

    private String country;
}
