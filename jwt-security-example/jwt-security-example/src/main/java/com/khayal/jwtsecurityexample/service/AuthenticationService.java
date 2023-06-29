package com.khayal.jwtsecurityexample.service;

import com.khayal.jwtsecurityexample.dto.UserDto;
import com.khayal.jwtsecurityexample.dto.UserRequest;
import com.khayal.jwtsecurityexample.dto.UserResponse;
import com.khayal.jwtsecurityexample.enums.Role;
import com.khayal.jwtsecurityexample.model.User;
import com.khayal.jwtsecurityexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    public UserResponse save(UserDto userDto) {

        User user=User.builder().username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .surname(userDto.getSurname())
                .role(Role.USER).build();
        userRepository.save(user);
        var token=jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();

        }

    public UserResponse auth(UserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}
