package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.NewsDtos.NewsRequest;
import Project.GestioneAziendale.Entities.News;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsMapper {
    @Autowired
    DipendeteRepository dipendeteRepository;

    public News fromNewsRequest(NewsRequest newsRequest){
        return News
                .builder()
                .titolo(newsRequest.titolo())
                .contenuto(newsRequest.contenuto())
                .dipendente(dipendeteRepository.findById(newsRequest.id_dipendente())
                        .orElseThrow(()-> new EntityNotFoundException("Dipendente con id " + newsRequest.id_dipendente() + "non trovato")))
                .immagine(newsRequest.immagine())
                .build();
    }
}
