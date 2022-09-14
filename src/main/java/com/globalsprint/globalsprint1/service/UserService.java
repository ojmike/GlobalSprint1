package com.globalsprint.globalsprint1.service;

import com.globalsprint.globalsprint1.model.User;
import com.globalsprint.globalsprint1.payload.request.CreateUserRequest;
import com.globalsprint.globalsprint1.payload.request.DeleteUserRequest;
import com.globalsprint.globalsprint1.payload.request.UpdateUserRequest;
import com.globalsprint.globalsprint1.payload.response.APIResponse;
import com.globalsprint.globalsprint1.payload.response.CreateUserResponse;
import com.globalsprint.globalsprint1.payload.response.GetAllUserResponse;
import com.globalsprint.globalsprint1.payload.response.GetUserResponse;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    APIResponse deleteUser(DeleteUserRequest deleteUserRequest);

    APIResponse updateUser(UpdateUserRequest updateUserRequest);

    GetUserResponse getUser(long id);
    GetAllUserResponse getUsers();

    User getLoggedInUser();
}
