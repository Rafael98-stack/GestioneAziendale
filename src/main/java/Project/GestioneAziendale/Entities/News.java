package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class News {
    @Id
    @GeneratedValue
    private Long id;
    private String titolo;
    private String contenuto;

    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;

    @ManyToMany(mappedBy = "newses")
    private List<Commento> commenti;
}
