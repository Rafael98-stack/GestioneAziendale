package Project.GestioneAziendale.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Dipendente {
    @Id
    @GeneratedValue
    private Long Id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private LocalDate data_nascita;
    private String luogo_nascita;
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
