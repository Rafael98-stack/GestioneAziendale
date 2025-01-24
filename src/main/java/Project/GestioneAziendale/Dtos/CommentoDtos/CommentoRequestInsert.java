package Project.GestioneAziendale.Dtos.CommentoDtos;

public record CommentoRequestInsert(

        String contenuto,

        Long id_dipendente,

        Long id_newse
) {
}
