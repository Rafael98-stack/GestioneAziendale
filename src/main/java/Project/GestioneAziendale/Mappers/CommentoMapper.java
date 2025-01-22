package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoRequestInsert;
import Project.GestioneAziendale.Entities.Commento;
import Project.GestioneAziendale.Services.DipendenteService;
import Project.GestioneAziendale.Services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentoMapper {
    @Autowired
    DipendenteService dipendenteService;
    @Autowired
    NewsService newsService;
    public Commento fromCommentoRequestInsert(CommentoRequestInsert commentoRequestInsert){
        return Commento
                .builder()
                .contenuto(commentoRequestInsert.contenuto())
                .dipendente(dipendenteService.getDipendenteById(commentoRequestInsert.id_dipendente()))
                .id_news(commentoRequestInsert.id_newse())
                .build();
    }
}
