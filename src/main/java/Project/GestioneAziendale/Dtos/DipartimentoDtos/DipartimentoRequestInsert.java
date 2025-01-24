package Project.GestioneAziendale.Dtos.DipartimentoDtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record DipartimentoRequestInsert(
        @NotBlank(message = "Il nome del dipartimento non può essere blank o null")
        String nome,
        @NotBlank(message = "La descrizione del dipartimento non può essere blank o null")
        String descrizione,
        Set<Long> id_posizione_lavorativa
) {
}
