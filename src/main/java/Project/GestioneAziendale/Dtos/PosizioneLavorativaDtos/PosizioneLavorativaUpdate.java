package Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record PosizioneLavorativaUpdate(
        @NotBlank
        String nome,
        @NotBlank
        String descrizione,
        @NotEmpty
        Set<Long> dipartimenti
) {
}
