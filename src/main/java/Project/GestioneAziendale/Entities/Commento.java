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
public class Commento {
    @Id
    @GeneratedValue
    private Long id;
    private String contenuto;

    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;

    @ManyToMany
    @JoinTable(name = "commento_news",
    joinColumns = @JoinColumn(name = "id_commento"),
            inverseJoinColumns = @JoinColumn(name = "id_news")
    )
    private List<News> newses;
}
