package Project.GestioneAziendale.Dtos.DipendenteDtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DipendenteRequestRegister(
        @NotBlank(message = "Il nome non può essere blank o null")
        String nome,
        @NotBlank(message = "Il cognome non può essere blank o null")
        String cognome,
        @Email(message = "Email non valida")
        String email,
        String password,
        @Past(message = "La data di nascita deve essere nel passato")
        LocalDate data_nascita,
        @NotBlank(message = "Luogo di nascita non può essere null o blank")
        String luogo_nascita,
        @Pattern(
                regexp = "^\\+?[0-9]+$",
                message = "Telefono non valido")
        String telefono,
        String immagine_profilo,
        Long id_dipartimento,
        Long id_posizione
) {
}
