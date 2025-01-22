package Project.GestioneAziendale.Dtos;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String exception,
        String message
) {
}
