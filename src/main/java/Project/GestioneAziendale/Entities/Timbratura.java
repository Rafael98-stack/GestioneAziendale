package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

public class Timbratura {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime orario_entrata;
    private LocalDateTime inizio_pranzo;
    private LocalDateTime fine_pranzo;
    private LocalDateTime uscita;
    private LocalDate data_corrente;

    @OneToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;
}
