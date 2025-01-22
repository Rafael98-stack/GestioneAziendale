package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoRequestInsert;
import Project.GestioneAziendale.Entities.Commento;
import Project.GestioneAziendale.Services.DipendenteService;
import Project.GestioneAziendale.Services.NewsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
                .newses(commentoRequestInsert.id_newses().stream().map(id -> {
                    try {
                        return newsService.getNewsById(id);
                    } catch (EntityNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList()))
                .build();
    }
}
