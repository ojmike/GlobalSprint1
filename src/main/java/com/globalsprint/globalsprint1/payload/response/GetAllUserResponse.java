package com.globalsprint.globalsprint1.payload.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class GetAllUserResponse {
    List<GetUserResponse> userResponses;
}
