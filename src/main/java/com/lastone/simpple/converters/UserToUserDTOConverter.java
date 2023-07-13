package com.lastone.simpple.converters;

import com.lastone.simpple.dto.UserDTO;
import com.lastone.simpple.model.SimpleUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTOConverter implements Converter<SimpleUser, UserDTO> {

    @Override
    public UserDTO convert(SimpleUser source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(source.getEmail());
        userDTO.setRole(source.getRole().name());
        return userDTO;
    }
}
