package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.NewsDtos.NewsRequest;
import Project.GestioneAziendale.Entities.News;
import Project.GestioneAziendale.Services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsMapper {
    @Autowired
    DipendenteService dipendenteService;

    public News fromNewsRequest(NewsRequest newsRequest){
        return  News
                .builder()
                .titolo(newsRequest.titolo())
                .contenuto(newsRequest.contenuto())
                .dipendente(dipendenteService.getDipendenteById(newsRequest.id_dipendente()))
                .build();
    }
}
