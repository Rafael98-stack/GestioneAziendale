package Project.GestioneAziendale.Repositories;

import Project.GestioneAziendale.Entities.PosizioneLavorativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PosizioneLavorativaRepository extends JpaRepository<PosizioneLavorativa,Long> {
}
