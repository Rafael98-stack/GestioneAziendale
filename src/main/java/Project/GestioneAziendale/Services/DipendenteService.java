package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestRegister;
import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestUpdate;
import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteResponse;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Mappers.DipendenteMapper;
import Project.GestioneAziendale.Repositories.DipartimentoRepository;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import Project.GestioneAziendale.Repositories.PosizioneLavorativaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {


    private final DipendenteMapper dipendenteMapper;

    private final DipendeteRepository dipendeteRepository;


    private final DipartimentoRepository dipartimentoRepository;

    private final PosizioneLavorativaRepository posizioneLavorativaRepository;
    @Autowired
    public DipendenteService(DipendenteMapper dipendenteMapper, DipendeteRepository dipendeteRepository, DipartimentoRepository dipartimentoRepository, PosizioneLavorativaRepository posizioneLavorativaRepository) {
        this.dipendenteMapper = dipendenteMapper;
        this.dipendeteRepository = dipendeteRepository;
        this.dipartimentoRepository = dipartimentoRepository;
        this.posizioneLavorativaRepository = posizioneLavorativaRepository;
    }

    public DipendenteResponse registerDipendente(DipendenteRequestRegister dipendenteRequestRegister){
        Dipendente dipendente = dipendenteMapper.fromDipendenteRequestRegister(dipendenteRequestRegister);
      return DipendenteResponse
              .builder()
              .id(dipendeteRepository.save(dipendente).getId())
              .build();
    }

    public Dipendente getDipendenteById(Long id_dipendente){
        return dipendeteRepository.findById(id_dipendente)
                .orElseThrow(() -> new EntityNotFoundException("dipendente con id " + id_dipendente + " non trovato"));
    }

    public List<Dipendente> getAllDipendenti(){
        return dipendeteRepository.findAll();
    }

    public DipendenteResponse updateDipendeteById(Long id_dipendente, DipendenteRequestUpdate dipendenteRequestUpdate){
        Dipendente dipendente = dipendeteRepository.findById(id_dipendente)
                .orElseThrow(() -> new EntityNotFoundException("dipendente con id " + id_dipendente + " non trovato"));
        dipendente.setCognome(dipendenteRequestUpdate.cognome());
        dipendente.setEmail(dipendenteRequestUpdate.email());
        dipendente.setDipartimento(dipartimentoRepository.findById(dipendenteRequestUpdate.id_dipartimento())
                .orElseThrow(() -> new EntityNotFoundException("Dipartimento con id " + dipendenteRequestUpdate.id_dipartimento() + " non trovato")));
        dipendente.setNome(dipendenteRequestUpdate.nome());
        dipendente.setData_nascita(dipendenteRequestUpdate.data_nascita());
        dipendente.setPassword(dipendenteRequestUpdate.password());
        dipendente.setTelefono(dipendenteRequestUpdate.telefono());
        dipendente.setImmagine_profilo(dipendenteRequestUpdate.immagine_profilo());
        dipendente.setLuogo_nascita(dipendenteRequestUpdate.luogo_nascita());
        dipendente.setPosizioneLavorativa(posizioneLavorativaRepository
                .findById(dipendenteRequestUpdate.id_posizione())
                .orElseThrow(()-> new EntityNotFoundException("Posizione lavorativa con id " + dipendenteRequestUpdate.id_posizione() + " non trovato")));

        return DipendenteResponse
                .builder()
                .id(dipendeteRepository.save(dipendente).getId())
                .build();
    }

    public void removeDipendenteById(Long id_dipendente){
        dipendeteRepository.deleteById(id_dipendente);
    }
}
