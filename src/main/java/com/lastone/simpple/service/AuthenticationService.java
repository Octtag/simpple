package com.lastone.simpple.service;

import com.lastone.simpple.dto.AuthenticationResponse;
import com.lastone.simpple.dto.LoginRequest;
import com.lastone.simpple.dto.SignUpRequest;

public interface AuthenticationService {
    AuthenticationResponse login(LoginRequest loginRequest);
    AuthenticationResponse signUp(SignUpRequest signUpRequest);

}
