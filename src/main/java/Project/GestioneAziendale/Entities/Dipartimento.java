package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Dipartimento {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descrizione;

    @ManyToMany
    @JoinTable(
            name = "dipartimento_posizione",
            joinColumns = @JoinColumn(name = "id_dipartimento"),
            inverseJoinColumns = @JoinColumn(name = "id_posizione")
    )
    private Set<PosizioneLavorativa> posizioniLavorative = new HashSet<>();
}
