package Project.GestioneAziendale.Dtos.CommentoDtos;

import jakarta.validation.constraints.NotBlank;

public record CommentoRequestInsert(
        @NotBlank(message = "Il contenuto non puo essere null o blank")
        String contenuto,
        @NotBlank(message = "L'id del dipendente non può essere null o blank")
        Long id_dipendente,
        @NotBlank(message = "Almeno un id di news è necessario")
        Long id_newse
) {
}
