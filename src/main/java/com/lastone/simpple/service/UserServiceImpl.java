package com.lastone.simpple.service;

import com.lastone.simpple.dto.PageResponseDTO;
import com.lastone.simpple.dto.SignUpRequest;
import com.lastone.simpple.dto.UserDTO;
import com.lastone.simpple.model.SimpleUser;
import com.lastone.simpple.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import static com.lastone.simpple.model.Role.USER;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConversionService conversionService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        SimpleUser simpleUser = userRepository.findByEmail(email).get();

        if (simpleUser == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return simpleUser;
    }

    @Override
    public UserDetails createUser(SignUpRequest signUpRequest) {
        try{
            return userRepository.save(SimpleUser.builder()
                    .email(signUpRequest.getEmail())
                    .username(signUpRequest.getUsername())
                    .dni(signUpRequest.getDni())
                    .celular(signUpRequest.getCelular())
                    .password(passwordEncoder.encode(signUpRequest.getPassword()))
                    .enabled(true)
                    .role(USER)
                    .build());
        }catch (DataIntegrityViolationException e){
            throw new ErrorResponseException(HttpStatus.CONFLICT,
                    ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                            "User already exist"),e);
        }
    }

    @Override
    public PageResponseDTO<UserDTO> getUsers(Pageable pageable) {

        Page<SimpleUser> userPage = userRepository.findAll(pageable);
        return new PageResponseDTO<>(
                userPage.getContent().stream()
                        .map(user -> conversionService.convert(user, UserDTO.class)).toList()
                , userPage.getPageable()
                , userPage.getTotalElements());
    }
}
