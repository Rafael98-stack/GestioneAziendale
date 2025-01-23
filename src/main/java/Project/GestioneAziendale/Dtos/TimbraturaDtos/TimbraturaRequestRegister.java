package Project.GestioneAziendale.Dtos.TimbraturaDtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TimbraturaRequestRegister(

        @NotNull(message = "L'ID non può essere nullo")
        Long id,

        @PastOrPresent(message = "L'orario di entrata deve essere nel passato o nel presente")
        LocalDateTime orario_entrata,

        @NotNull(message = "L'orario di inizio della pausa pranzo non può essere nullo")
        @FutureOrPresent(message = "L'orario di inizio della pausa pranzo deve essere nel presente o nel futuro")
        LocalDateTime inizio_pranzo,

        @NotNull(message = "L'orario di fine della pausa pranzo non può essere nullo")
        @Future(message = "L'orario di fine della pausa pranzo deve essere nel futuro")
        LocalDateTime fine_pranzo,

        @NotNull(message = "L'orario di uscita non può essere nullo")
        @Future(message = "L'orario di uscita deve essere nel futuro")
        LocalDateTime uscita,

        @NotNull(message = "La data corrente non può essere nulla")
        @PastOrPresent(message = "La data corrente deve essere nel passato o nel presente")
        LocalDate data_corrente

) {
}
