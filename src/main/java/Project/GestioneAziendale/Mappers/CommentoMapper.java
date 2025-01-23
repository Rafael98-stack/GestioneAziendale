package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoRequestInsert;
import Project.GestioneAziendale.Entities.Commento;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import Project.GestioneAziendale.Repositories.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentoMapper {

    private final DipendeteRepository dipendeteRepository;

    private final NewsRepository newsRepository;

    @Autowired
    public CommentoMapper(DipendeteRepository dipendeteRepository, NewsRepository newsRepository) {
        this.dipendeteRepository = dipendeteRepository;
        this.newsRepository = newsRepository;
    }

    public Commento fromCommentoRequestInsert(CommentoRequestInsert commentoRequestInsert){
        return Commento
                .builder()
                .contenuto(commentoRequestInsert.contenuto())
                .dipendente(dipendeteRepository.findById(commentoRequestInsert.id_dipendente())
                        .orElseThrow(()-> new EntityNotFoundException("Dipendente con id " + commentoRequestInsert.id_dipendente() + " non trovato")))
                .news(newsRepository.findById(commentoRequestInsert.id_newse())
                        .orElseThrow(()-> new EntityNotFoundException("News con id " + commentoRequestInsert.id_newse() + " non trovato")))
                .build();
    }
}
