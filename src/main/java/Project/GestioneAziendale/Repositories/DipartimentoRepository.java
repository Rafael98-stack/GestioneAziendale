package Project.GestioneAziendale.Repositories;

import Project.GestioneAziendale.Entities.Dipartimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DipartimentoRepository extends JpaRepository<Dipartimento,Long> {
}
