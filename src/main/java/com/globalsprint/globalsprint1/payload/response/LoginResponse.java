package com.globalsprint.globalsprint1.payload.response;

import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
public class LoginResponse {

    private final String jwt;
}
