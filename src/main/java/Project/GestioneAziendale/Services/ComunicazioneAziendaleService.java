package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoInsertUpdate;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoRequestInsert;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoResponse;
import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleRequest;
import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleResponse;
import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleUpdate;
import Project.GestioneAziendale.Entities.Commento;
import Project.GestioneAziendale.Entities.ComunicazioneAziendale;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Mappers.ComunicazioneAziendaleMapper;
import Project.GestioneAziendale.Repositories.ComunicazioneAziendaleRepository;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunicazioneAziendaleService {

    ComunicazioneAziendaleRepository comunicazioneAziendaleRepository;

    DipendeteRepository dipendeteRepository;

ComunicazioneAziendaleMapper comunicazioneAziendaleMapper;

@Autowired
public ComunicazioneAziendaleService(ComunicazioneAziendaleRepository comunicazioneAziendaleRepository,DipendeteRepository dipendeteRepository,ComunicazioneAziendaleMapper comunicazioneAziendaleMapper) {
this.comunicazioneAziendaleRepository = comunicazioneAziendaleRepository;
this.dipendeteRepository = dipendeteRepository;
this.comunicazioneAziendaleMapper = comunicazioneAziendaleMapper;
}

public ComunicazioneAziendaleResponse insertComunicazione(ComunicazioneAziendaleRequest comunicazioneAziendaleRequest){
ComunicazioneAziendale comunicazioneAziendale = comunicazioneAziendaleMapper.fromComunicazioneRequest(comunicazioneAziendaleRequest);
return ComunicazioneAziendaleResponse
                .builder()
                .id(comunicazioneAziendaleRepository.save(comunicazioneAziendale).getId())
                .build();
    }

public ComunicazioneAziendale getComunicazioneById(Long id_comunicazione){
return comunicazioneAziendaleRepository.findById(id_comunicazione)
            .orElseThrow(()-> new EntityNotFoundException("Comunicazione con id " + id_comunicazione + " non trovato"));
    }

public List<ComunicazioneAziendale> getAllComunicazioni(){
    return comunicazioneAziendaleRepository.findAll();
    }

public ComunicazioneAziendaleResponse updateComunicazioneById(Long id_comunicazione, ComunicazioneAziendaleUpdate comunicazioneAziendaleUpdate){
    ComunicazioneAziendale comunicazioneAziendale = comunicazioneAziendaleRepository.findById(id_comunicazione)
            .orElseThrow(()-> new EntityNotFoundException("Comunicazione con id " + id_comunicazione + " non trovato"));
        comunicazioneAziendale.setContenuto(comunicazioneAziendale.getContenuto());
        comunicazioneAziendale.setDipendente(dipendeteRepository.findById(comunicazioneAziendaleUpdate.id_dipendente())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente con id " + comunicazioneAziendaleUpdate.id_dipendente() + " non trovato")));
comunicazioneAziendale.setTitolo(comunicazioneAziendaleUpdate.titolo());


        return ComunicazioneAziendaleResponse
                .builder()
                .id(comunicazioneAziendaleRepository.save(comunicazioneAziendale).getId())
                .build();
    }

    public void removeComunicazioneById(Long id_comunicazione){
comunicazioneAziendaleRepository.deleteById(id_comunicazione);
    }

}
