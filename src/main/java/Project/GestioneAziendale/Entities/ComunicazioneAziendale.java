package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "comunicazione")
public class ComunicazioneAziendale {
    @Id
    @GeneratedValue
    private Long id;
    private String titolo;
    private String contenuto;

    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;
}
