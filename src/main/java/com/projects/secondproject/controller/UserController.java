package com.projects.secondproject.controller;

import com.projects.secondproject.dto.request.RequestUserDto;
import com.projects.secondproject.service.UserService;
import com.projects.secondproject.util.AuthenticationResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/secondprojects")
@CrossOrigin(allowedHeaders = "*" ,origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> createUser(
            @RequestBody RequestUserDto requestUserDto
    ){
        return ResponseEntity.ok(userService.createUser(requestUserDto)) ;

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse > login(
            @RequestBody RequestUserDto requestUserDto
    ){
            AuthenticationResponse response = userService.login(requestUserDto);
            return ResponseEntity.ok(response);
    }




}
