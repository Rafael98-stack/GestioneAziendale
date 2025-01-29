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
    @JoinColumn(name = "id_news", nullable = false)
    private News news;;

    @ManyToOne()
    @JoinColumn(name = "id_scheduled_news")
    private NewsScheduled news_scheduled;

}
