package com.globalsprint.globalsprint1.controller;

import com.globalsprint.globalsprint1.payload.request.CreateUserRequest;
import com.globalsprint.globalsprint1.payload.request.DeleteUserRequest;
import com.globalsprint.globalsprint1.payload.request.UpdateUserRequest;
import com.globalsprint.globalsprint1.payload.response.APIResponse;
import com.globalsprint.globalsprint1.payload.response.CreateUserResponse;
import com.globalsprint.globalsprint1.payload.response.GetAllUserResponse;
import com.globalsprint.globalsprint1.payload.response.GetUserResponse;
import com.globalsprint.globalsprint1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public CreateUserResponse createUser(@Validated @RequestBody CreateUserRequest createUserRequest){
       return userService.createUser(createUserRequest);
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @RolesAllowed("ROLE_ADMIN")
    public APIResponse deleteUser(@Validated @RequestBody DeleteUserRequest deleteUserRequest){
        return userService.deleteUser(deleteUserRequest);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse updateUser(@Validated @RequestBody UpdateUserRequest updateUserRequest){
        return userService.updateUser(updateUserRequest);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed("ROLE_ADMIN")
    @ResponseStatus(value = HttpStatus.OK)
    public GetUserResponse getUser(@RequestParam(name = "id") long id){
        return userService.getUser(id);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed("ROLE_ADMIN")
    @ResponseStatus(value = HttpStatus.OK)
    public GetAllUserResponse getUsers(){
        return userService.getUsers();
    }


}


