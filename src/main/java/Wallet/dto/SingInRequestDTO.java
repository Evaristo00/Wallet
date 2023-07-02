package Wallet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SingInRequestDTO(@NotBlank @Email String email,@NotBlank @Email String password) {
}
