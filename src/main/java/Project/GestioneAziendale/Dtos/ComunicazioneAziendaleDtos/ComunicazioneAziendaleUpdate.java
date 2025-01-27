package Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos;

import Project.GestioneAziendale.Entities.Dipendente;

public record ComunicazioneAziendaleUpdate(
        String titolo,
        String contenuto,
    Long id_dipendente
) {
}
