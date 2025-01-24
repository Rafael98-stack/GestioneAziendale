package Project.GestioneAziendale.Dtos.NewsDtos;

public record NewsUpdate(
        String titolo,
        String contenuto,
        Long id_dipendente,
        String immagine
) {
}
