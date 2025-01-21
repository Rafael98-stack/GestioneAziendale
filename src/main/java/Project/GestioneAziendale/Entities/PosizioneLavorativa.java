package Project.GestioneAziendale.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PosizioneLavorativa {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descrizione;

    @ManyToMany(mappedBy = "posizioni_lavorative")
    private Set<Dipartimento> dipartimenti = new HashSet<>();
}
