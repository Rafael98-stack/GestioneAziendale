package Project.GestioneAziendale.Repositories;

import Project.GestioneAziendale.Entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DipendeteRepository extends JpaRepository<Dipendente,Long> {

    Optional<Dipendente> findByEmail(String email);
    Optional<Dipendente> findByRegistrationToken(String token);
}
