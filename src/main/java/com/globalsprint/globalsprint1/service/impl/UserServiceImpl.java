package com.globalsprint.globalsprint1.service.impl;

import com.globalsprint.globalsprint1.exception.BadRequestException;
import com.globalsprint.globalsprint1.model.Role;
import com.globalsprint.globalsprint1.model.User;
import com.globalsprint.globalsprint1.payload.request.CreateUserRequest;
import com.globalsprint.globalsprint1.payload.request.DeleteUserRequest;
import com.globalsprint.globalsprint1.payload.request.UpdateUserRequest;
import com.globalsprint.globalsprint1.payload.response.APIResponse;
import com.globalsprint.globalsprint1.payload.response.CreateUserResponse;
import com.globalsprint.globalsprint1.payload.response.GetAllUserResponse;
import com.globalsprint.globalsprint1.payload.response.GetUserResponse;
import com.globalsprint.globalsprint1.repository.RoleRepository;
import com.globalsprint.globalsprint1.repository.UserRepository;
import com.globalsprint.globalsprint1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {

        if(ensureEmailIsUnique(createUserRequest.getEmail())) throw new BadRequestException("Email already exist in the system");
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());
        List<Role> roles = Arrays.asList(roleRepository.findByName("ROLE_USER"));
        User user = User.builder().
                firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .country(createUserRequest.getCountry())
                .dateOfBirth(createUserRequest.getDateOfBirth())
                .email(createUserRequest.getEmail())
                .gender(createUserRequest.getGender())
                .password(encodedPassword)
                .roles(roles)
                .build();
        user = userRepository.save(user);
        String message = format("User with name %s created successfully",user.getFirstName());
        return  CreateUserResponse.builder()
                .code(HttpStatus.CREATED.name())
                .userId(user.getId())
                .message(message)
                .build();
    }

    private boolean ensureEmailIsUnique(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public APIResponse deleteUser(DeleteUserRequest deleteUserRequest) {
        User user = userRepository.findByIdAndEmail(deleteUserRequest.getUserId(),deleteUserRequest.getEmailAddress());
        if(!ObjectUtils.isEmpty(user)){
            userRepository.delete(user);
            String message = format("User with name %s deleted successfully",user.getFirstName());
            return APIResponse.builder()
                    .message(message)
                    .build();
        }else{
        throw  new RuntimeException();
        }
    }

    @Override
    public APIResponse updateUser(UpdateUserRequest updateUserRequest) {

        User user =  userRepository.findById(updateUserRequest.getUserId()).orElseThrow(() -> new BadRequestException("User does not exist in the system"));

        if(!hasRole("ROLE_ADMIN") || !getLoggedInUser().equals(user)) throw new BadRequestException("User cannot perform operation");


        if(userRepository.existsByEmailAndIdNot(updateUserRequest.getEmail(), updateUserRequest.getUserId())) throw new BadRequestException("Email already exist in the system and belongs to another user");

        String encodedPassword = passwordEncoder.encode(updateUserRequest.getPassword());

        user = User.builder().
                firstName(updateUserRequest.getFirstName())
                .lastName(updateUserRequest.getLastName())
                .country(updateUserRequest.getCountry())
                .dateOfBirth(updateUserRequest.getDateOfBirth())
                .email(updateUserRequest.getEmail())
                .gender(updateUserRequest.getGender())
                .password(encodedPassword)
                .build();

        return APIResponse.builder()
                .message(String.format("User with name %s updated successfully",user.getFirstName()))
                .build();

    }

    @Override
    public GetUserResponse getUser(long id) {
        Optional<User> user =  userRepository.findById(id);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("Not found %s",id)));
        return  user.map(GetUserResponse::new).get();
    }

    @Override
    public GetAllUserResponse getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        System.out.println("roles = " + roles);
        List<GetUserResponse> getUserResponseList = userRepository.findAll()
                .stream()
                .map(GetUserResponse::new)
                .toList();
        GetAllUserResponse getAllUserResponse = new GetAllUserResponse(getUserResponseList);
        return getAllUserResponse;

    }


    public User getLoggedInUser(){
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new BadRequestException("No logged in user found"));
    }

    private boolean hasRole (String roleName) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
    }

}
