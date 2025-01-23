package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PosizioneLavorativa {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descrizione;

    @ManyToMany(mappedBy = "posizioniLavorative")
    private Set<Dipartimento> dipartimenti;
}

