package Project.GestioneAziendale.Repositories;

import Project.GestioneAziendale.Entities.ComunicazioneAziendale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicazioneAziendaleRepository extends JpaRepository<ComunicazioneAziendale,Long> {
}
