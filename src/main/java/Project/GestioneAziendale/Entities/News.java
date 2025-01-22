package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

public class News {
    @Id
    @GeneratedValue
    private Long id;
    private String titolo;
    private String contenuto;
    private Long likes = 0L;
    private String immagine;

    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;

    @ManyToMany(mappedBy = "newses")
    private List<Commento> commenti = new ArrayList<>();
}
