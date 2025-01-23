package Project.GestioneAziendale.Dtos.TimbraturaDtos;

import jakarta.validation.constraints.NotNull;

public record TimbraturaRequestUpdate (
        @NotNull(message = "L'ID non può essere nullo")
        Long id_dipendente
)
{


}
