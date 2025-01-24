package Project.GestioneAziendale.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    // Da ricordare che mappedBy vuole esattamente il nome della variabile presente nell'altra entità in cui è influenzata la relazione
    // Come nel nostro caso nell'entità Dipartimento:  private Set<PosizioneLavorativa> posizioniLavorative = new HashSet<>();
    @ManyToMany(mappedBy = "posizioniLavorative")
    private Set<Dipartimento> dipartimenti;

}

