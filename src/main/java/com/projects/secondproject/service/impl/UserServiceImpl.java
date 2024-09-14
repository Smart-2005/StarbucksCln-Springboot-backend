package com.projects.secondproject.service.impl;

import com.projects.secondproject.dto.request.RequestUserDto;
import com.projects.secondproject.entity.Role;
import com.projects.secondproject.entity.User;
import com.projects.secondproject.jwt.JwtService;
import com.projects.secondproject.repo.UserRepository;
import com.projects.secondproject.service.UserService;
import com.projects.secondproject.util.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse createUser(RequestUserDto requestUserDto) {
        User user = User.builder()
                .name(requestUserDto.getName())
                .username(requestUserDto.getUsername())
                .password(passwordEncoder.encode( requestUserDto.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser =  userRepository.save(user);
        String token = jwtService.generateToke(savedUser);
        return new AuthenticationResponse(token);

    }

    @Override
    public AuthenticationResponse login(RequestUserDto requestUserDto){

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestUserDto.getUsername(),
                            requestUserDto.getPassword()
                    )
            );

        User user = userRepository.findByUsername(requestUserDto.getUsername()).orElseThrow();
        String token = jwtService.generateToke(user);
        return new AuthenticationResponse(token);
    }



}
