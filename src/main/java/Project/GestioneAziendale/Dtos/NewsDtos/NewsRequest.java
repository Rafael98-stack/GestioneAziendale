package Project.GestioneAziendale.Dtos.NewsDtos;

public record NewsRequest(
        String titolo,
        String contenuto,
        Long id_dipendente
) {
}
