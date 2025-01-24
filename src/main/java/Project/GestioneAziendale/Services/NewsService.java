package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Mappers.NewsMapper;
import Project.GestioneAziendale.Repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    CommentoService commentoService;

}
