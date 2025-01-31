package Project.GestioneAziendale.Dtos;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token
) {
}
