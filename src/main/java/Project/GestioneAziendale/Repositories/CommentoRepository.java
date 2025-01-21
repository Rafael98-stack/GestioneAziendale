package Project.GestioneAziendale.Repositories;

import Project.GestioneAziendale.Entities.Commento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentoRepository extends JpaRepository<Commento,Long> {
}
