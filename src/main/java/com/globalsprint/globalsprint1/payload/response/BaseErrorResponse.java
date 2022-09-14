package com.globalsprint.globalsprint1.payload.response;

import com.globalsprint.globalsprint1.payload.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class BaseErrorResponse {
    public APIResponse buildErrorResponse(String message) {
        return buildErrorResponse(ResponseCode.INTERNAL_SERVER_ERROR.getCode(), message);
    }
    public APIResponse buildErrorResponse(String message, Throwable e) {
        log.error(e.getMessage(), e);
        return buildErrorResponse(message);
    }
    public APIResponse buildErrorResponse(String code, String message, Throwable e) {
        log.error(e.getMessage(), e);
        return buildErrorResponse(code, message);
    }
    private APIResponse buildErrorResponse(String code, String message) {
        return APIResponse.builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }
}