package Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos;

import Project.GestioneAziendale.Entities.Dipendente;
import lombok.Builder;

@Builder
public record ComunicazioneAziendaleRequest(
        String titolo,
        String contenuto,
    Long id_dipendente
) {
}
