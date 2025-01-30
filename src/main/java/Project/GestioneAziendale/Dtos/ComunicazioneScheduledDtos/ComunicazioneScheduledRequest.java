package Project.GestioneAziendale.Dtos.ComunicazioneScheduledDtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ComunicazioneScheduledRequest(
        @NotNull
        Long id_dipendente,
        @NotBlank
        String titolo,
        @NotBlank
        String contenuto,
        @NotNull
        @Future
        LocalDateTime publishTime

) {
}
