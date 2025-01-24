package Project.GestioneAziendale.Dtos.NewsDtos;

import Project.GestioneAziendale.Entities.Dipendente;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record NewsRequest(
        @NotBlank
        String titolo,
        @NotBlank
        String contenuto,
        @NotBlank
        Dipendente dipendente
) {
}
