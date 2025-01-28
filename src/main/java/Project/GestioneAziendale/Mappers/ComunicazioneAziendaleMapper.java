package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleRequest;
import Project.GestioneAziendale.Entities.ComunicazioneAziendale;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.DipendenteNotFoundException;
import Project.GestioneAziendale.Repositories.CommentoRepository;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import org.springframework.stereotype.Service;

@Service
public class ComunicazioneAziendaleMapper {

    DipendeteRepository dipendeteRepository;

    CommentoRepository commentoRepository;

    public ComunicazioneAziendaleMapper(DipendeteRepository dipendeteRepository, CommentoRepository commentoRepository) {
        this.dipendeteRepository = dipendeteRepository;
        this.commentoRepository = commentoRepository;
    }


    public ComunicazioneAziendale fromComunicazioneRequest(ComunicazioneAziendaleRequest comunicazioneAziendaleRequest){
    return ComunicazioneAziendale
                .builder()
            .contenuto(comunicazioneAziendaleRequest.contenuto())
            .dipendente(dipendeteRepository.findById(comunicazioneAziendaleRequest.id_dipendente())
                    .orElseThrow(() -> new DipendenteNotFoundException("Dipendente con id " + comunicazioneAziendaleRequest.id_dipendente() + " non trovato")))
        .titolo(comunicazioneAziendaleRequest.titolo())
                .build();
    }
}
