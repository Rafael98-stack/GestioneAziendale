package Project.GestioneAziendale.Dtos;

import lombok.Builder;

@Builder
public record ChangePasswordRequest(
        String oldPassword,
        String newPassword
) {
}
