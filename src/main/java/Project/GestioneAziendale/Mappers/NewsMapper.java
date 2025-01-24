package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.NewsDtos.NewsRequest;
import Project.GestioneAziendale.Entities.News;
import org.springframework.beans.factory.annotation.Autowired;

public class NewsMapper {
    @Autowired
    CommentoService commentoService;

    public News fromNewsRequest(NewsRequest request){
        return News
                .builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .dipendente()
                .build();
    }
}
