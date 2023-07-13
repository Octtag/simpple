package com.lastone.simpple.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    @Length(min = 4,max = 250)
    private String email;

    @NotEmpty
    private String dni;

    @NotEmpty
    private String celular;

    @NotEmpty
    @Length(min = 4,max = 50)
    private String password;

}
