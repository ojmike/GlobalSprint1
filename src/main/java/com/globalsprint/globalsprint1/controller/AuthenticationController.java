package com.globalsprint.globalsprint1.controller;

import com.globalsprint.globalsprint1.config.security.MyUserDetailsService;
import com.globalsprint.globalsprint1.payload.request.LoginRequest;
import com.globalsprint.globalsprint1.payload.response.LoginResponse;
import com.globalsprint.globalsprint1.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class AuthenticationController {

    private final JwtUtil jwtTokenUtil;

    private final MyUserDetailsService myUserDetailsService;

    private final AuthenticationManager authenticationManager;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) throws Exception {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getUsername(), loginRequest.getPassword()
                            )
                    );

            final UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginRequest.getUsername());
            final String jwtToken = jwtTokenUtil.generateToken(userDetails);
            LoginResponse loginResponse = new LoginResponse(jwtToken);
            return  ResponseEntity.ok(loginResponse);

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
