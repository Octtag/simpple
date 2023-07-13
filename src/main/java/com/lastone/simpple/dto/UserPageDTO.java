package com.lastone.simpple.dto;

import java.util.List;

public class UserPageDTO extends PageResponseDTO<UserDTO> {
    public UserPageDTO(List<UserDTO> content) {
        super(content);
    }
}
