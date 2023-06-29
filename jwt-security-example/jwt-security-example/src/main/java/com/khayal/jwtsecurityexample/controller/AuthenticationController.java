package com.khayal.jwtsecurityexample.controller;

import com.khayal.jwtsecurityexample.dto.UserDto;
import com.khayal.jwtsecurityexample.dto.UserRequest;
import com.khayal.jwtsecurityexample.dto.UserResponse;
import com.khayal.jwtsecurityexample.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto){
        return  ResponseEntity.ok(authenticationService.save(userDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody UserRequest request){

        return ResponseEntity.ok(authenticationService.auth(request));
    }

}
