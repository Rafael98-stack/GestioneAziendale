package Project.GestioneAziendale.Dtos.TimbraturaDtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TimbraturaRequestUpdate (
        @NotNull(message = "L'ID non può essere nullo")
        Long id,

        @PastOrPresent(message = "L'orario di entrata deve essere nel passato o nel presente")
        LocalDateTime Orario_entrata,

        @NotNull(message = "L'orario di inizio della pausa pranzo non può essere nullo")
        @FutureOrPresent(message = "L'orario di inizio della pausa pranzo deve essere nel presente o nel futuro")
        LocalDateTime Inizio_pranzo,

        @NotNull(message = "L'orario di fine della pausa pranzo non può essere nullo")
        @Future(message = "L'orario di fine della pausa pranzo deve essere nel futuro")
        LocalDateTime Fine_pranzo,

        @NotNull(message = "L'orario di uscita non può essere nullo")
        @Future(message = "L'orario di uscita deve essere nel futuro")
        LocalDateTime Uscita,

        @NotNull(message = "La data corrente non può essere nulla")
        @PastOrPresent(message = "La data corrente deve essere nel passato o nel presente")
        LocalDate data_corrente
)
{


}
