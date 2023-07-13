package com.lastone.simpple.service;

import com.lastone.simpple.dto.PageResponseDTO;
import com.lastone.simpple.dto.SignUpRequest;
import com.lastone.simpple.dto.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails createUser(SignUpRequest signUpRequest);

    PageResponseDTO<UserDTO> getUsers(Pageable pageable);
}
