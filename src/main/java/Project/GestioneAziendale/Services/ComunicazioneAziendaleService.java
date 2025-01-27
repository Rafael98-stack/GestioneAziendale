package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoInsertUpdate;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoRequestInsert;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoResponse;
import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleRequest;
import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleResponse;
import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleUpdate;
import Project.GestioneAziendale.Dtos.NewsDtos.NewsResponse;
import Project.GestioneAziendale.Entities.*;
import Project.GestioneAziendale.Mappers.ComunicazioneAziendaleMapper;
import Project.GestioneAziendale.Repositories.ComunicazioneAziendaleRepository;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import Project.GestioneAziendale.Repositories.PosizioneLavorativaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunicazioneAziendaleService {

    ComunicazioneAziendaleRepository comunicazioneAziendaleRepository;

    DipendeteRepository dipendeteRepository;

ComunicazioneAziendaleMapper comunicazioneAziendaleMapper;

PosizioneLavorativaRepository posizioneLavorativaRepository;

@Autowired
public ComunicazioneAziendaleService(PosizioneLavorativaRepository posizioneLavorativaRepository, ComunicazioneAziendaleRepository comunicazioneAziendaleRepository,DipendeteRepository dipendeteRepository,ComunicazioneAziendaleMapper comunicazioneAziendaleMapper) {
this.comunicazioneAziendaleRepository = comunicazioneAziendaleRepository;
this.dipendeteRepository = dipendeteRepository;
this.comunicazioneAziendaleMapper = comunicazioneAziendaleMapper;
this.posizioneLavorativaRepository = posizioneLavorativaRepository;
}

public ComunicazioneAziendaleResponse insertComunicazione(ComunicazioneAziendaleRequest comunicazioneAziendaleRequest) throws Exception {

    Dipendente dipendente = dipendeteRepository.findById(comunicazioneAziendaleRequest.id_dipendente())
            .orElseThrow(() -> new EntityNotFoundException("Dipendente non esiste"));

    PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository.findById(dipendente.getPosizioneLavorativa().getId())
            .orElseThrow(() -> new EntityNotFoundException("Posizione non esiste"));

    if (posizioneLavorativa.getNome().equalsIgnoreCase("publisher")) {
        ComunicazioneAziendale comunicazioneAziendale = comunicazioneAziendaleMapper.fromComunicazioneRequest(comunicazioneAziendaleRequest);
        return ComunicazioneAziendaleResponse
                .builder()
                .id(comunicazioneAziendaleRepository.save(comunicazioneAziendale).getId())
                .build();
    } else {
        throw new Exception("Non puoi creare una Comunicazione");
    }
}

public ComunicazioneAziendale getComunicazioneById(Long id_comunicazione){
return comunicazioneAziendaleRepository.findById(id_comunicazione)
            .orElseThrow(()-> new EntityNotFoundException("Comunicazione con id " + id_comunicazione + " non trovato"));
    }

public List<ComunicazioneAziendale> getAllComunicazioni(){
    return comunicazioneAziendaleRepository.findAll();
    }

public ComunicazioneAziendaleResponse updateComunicazioneById(Long id_comunicazione, ComunicazioneAziendaleUpdate comunicazioneAziendaleUpdate) throws Exception {
    Dipendente dipendente = dipendeteRepository.findById(comunicazioneAziendaleUpdate.id_dipendente())
            .orElseThrow(() -> new EntityNotFoundException("Dipendente non esiste"));

    PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository.findById(dipendente.getPosizioneLavorativa().getId())
            .orElseThrow(() -> new EntityNotFoundException("Posizione non esiste"));

    if (posizioneLavorativa.getNome().equalsIgnoreCase("publisher")) {
        ComunicazioneAziendale comunicazioneAziendale = comunicazioneAziendaleRepository
                .findById(id_comunicazione)
                .orElseThrow(() -> new EntityNotFoundException("Comunicazione non esiste"));
        comunicazioneAziendale.setTitolo(comunicazioneAziendaleUpdate.titolo());
        comunicazioneAziendale.setContenuto(comunicazioneAziendaleUpdate.contenuto());
        return ComunicazioneAziendaleResponse
                .builder()
                .id(comunicazioneAziendaleRepository.save(comunicazioneAziendale).getId())
                .build();
    } else {
        throw new Exception("Non puoi aggiornare una Comunicazione");
    }
    }

    public void removeComunicazioneById(Long id_comunicazione){
comunicazioneAziendaleRepository.deleteById(id_comunicazione);
    }

}
