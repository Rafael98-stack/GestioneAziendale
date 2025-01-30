package Project.GestioneAziendale.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AuthRequest(
        @Email(message = "Email non valida")
        String email,
        @NotBlank
        String password
) {
}
