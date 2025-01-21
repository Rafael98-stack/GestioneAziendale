package Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.Set;

@Builder
public record PosizioneLavorativaRequest(
        @NotBlank(message = "Il nome non puo essere blank o null")
        String nome,
        @NotBlank(message = "La descrizione non puo essere blank o null")
        String descrizione,
        @NotEmpty
        Set<Long> dipartimenti
) {
}
