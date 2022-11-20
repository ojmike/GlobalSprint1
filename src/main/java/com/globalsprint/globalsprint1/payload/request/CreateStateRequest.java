package com.globalsprint.globalsprint1.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStateRequest {
    private String name;

    private String capital;

    private String country;
}
