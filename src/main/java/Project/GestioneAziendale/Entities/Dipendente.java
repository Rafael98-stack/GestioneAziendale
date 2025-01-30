package Project.GestioneAziendale.Entities;

import Project.GestioneAziendale.Entities.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class Dipendente implements UserDetails {
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

    @ManyToOne
    @JoinColumn(name = "id_dipartimento")
    private Dipartimento dipartimento;

    @OneToMany(mappedBy = "dipendente")
    private List<Commento> commenti;

    @OneToMany(mappedBy = "dipendente")
    private Set<News> newses;

    @OneToMany(mappedBy = "dipendente")
    private Set<ComunicazioneAziendale> comunicazioni_aziendali;

    @ManyToOne()
    @JoinColumn(name = "id_posizione")
    private PosizioneLavorativa posizioneLavorativa;

    @ManyToOne()
    @JoinColumn(name = "id_tibratura")
    private Timbratura timbratura;

    @Column(name = "registration_token")
    private String registrationToken;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
