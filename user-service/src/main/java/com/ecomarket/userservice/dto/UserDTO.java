package com.ecomarket.userservice.dto;
import jakarta.validation.constraints.*;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDTO {
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String name;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email invÃ¡lido")
    @Size(max = 100)
    private String email;
    @NotBlank(message = "El rol es obligatorio")
    private String roleName;
}