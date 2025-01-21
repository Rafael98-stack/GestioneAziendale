package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class Dipendente {
    @Id
    @GeneratedValue
    private Long Id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate data_nascita;
    @Column(nullable = false)
    private String luogo_nascita;
    @Column(nullable = false, unique = true)
    private String telefono;
    private String immagine_profilo;

    @OneToOne
    @JoinColumn(name = "id_dipartimento")
    private Dipartimento dipartimento;

    @OneToMany(mappedBy = "dipendente")
    private List<Commento> commenti;

    @OneToMany(mappedBy = "dipendente")
    private Set<News> newses;

    @OneToMany(mappedBy = "dipendente")
    private Set<ComunicazioneAziendale> comunicazioni_aziendali;
}
