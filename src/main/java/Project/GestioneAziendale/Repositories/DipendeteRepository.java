package Project.GestioneAziendale.Repositories;

import Project.GestioneAziendale.Entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DipendeteRepository extends JpaRepository<Dipendente,Long> {
}
