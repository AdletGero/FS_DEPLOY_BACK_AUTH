package org.example.login_registration.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Sign in request")
public class SignInRequest {

    @Schema(description = "username", example = "Adlet")
    @Size(min = 5, max = 50, message = "Minimum 5 symbols, maximum 50")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(min = 6, max = 255, message = "Not longer than 255 symbols, min 6 symbols")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
