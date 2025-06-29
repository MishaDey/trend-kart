package com.trend_kart.modules.user.dto;

import com.trend_kart.utils.Update;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable {
    @NotNull(groups = Update.class, message = "ID cannot be blank while updating.")
    private UUID id;

    private String name;

    @NotBlank(message = "Email ID is missing.")
    @Email(message = "Not a valid mail.")
    private String email;

    @Min(value = 8, message = "Password cannot be less than 8 characters.")
    private String password;

    private String role;
}
