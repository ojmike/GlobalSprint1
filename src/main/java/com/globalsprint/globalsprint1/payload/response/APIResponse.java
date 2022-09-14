package com.globalsprint.globalsprint1.payload.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {
    private boolean success;
    private String message;
    private String code;
    private Object body;
}