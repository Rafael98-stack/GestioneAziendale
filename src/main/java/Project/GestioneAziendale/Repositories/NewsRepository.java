package Project.GestioneAziendale.Repositories;

import Project.GestioneAziendale.Entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Long> {
}
