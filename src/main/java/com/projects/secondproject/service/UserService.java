package com.projects.secondproject.service;

import com.projects.secondproject.dto.request.RequestUserDto;
import com.projects.secondproject.util.AuthenticationResponse;


public interface UserService {

     AuthenticationResponse createUser(RequestUserDto requestUserDto);
     AuthenticationResponse login(RequestUserDto requestUserDto);



}
