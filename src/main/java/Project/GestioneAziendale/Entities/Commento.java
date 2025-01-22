package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Commento {
    @Id
    @GeneratedValue
    private Long id;
    private String contenuto;

    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;

    @ManyToOne
    private Long id_news;

}
