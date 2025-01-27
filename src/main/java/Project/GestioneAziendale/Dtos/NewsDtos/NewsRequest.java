package Project.GestioneAziendale.Dtos.NewsDtos;

import Project.GestioneAziendale.Entities.Dipendente;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record NewsRequest(
        String titolo,
        String contenuto,
        Long  id_dipendente,
        String immagine
) {
}
