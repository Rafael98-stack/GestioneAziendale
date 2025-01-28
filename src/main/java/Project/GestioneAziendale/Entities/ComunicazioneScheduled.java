package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comunicazione_scheduled")
@EntityListeners(AuditingEntityListener.class)
public class ComunicazioneScheduled
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



}
