package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "news_scheduled")
@EntityListeners(AuditingEntityListener.class)
public class NewsScheduled
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String contenuto;
    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;
    @OneToMany(mappedBy = "news_scheduled")
    private List<Commento> commenti;
}
