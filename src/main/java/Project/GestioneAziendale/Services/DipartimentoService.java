package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.DipartimentoDtos.DipartimentoRequestInsert;
import Project.GestioneAziendale.Dtos.DipartimentoDtos.DipartimentoRequestUpdate;
import Project.GestioneAziendale.Entities.Dipartimento;
import Project.GestioneAziendale.Mappers.DipartimentoMapper;
import Project.GestioneAziendale.Repositories.DipartimentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DipartimentoService {

    @Autowired
    DipartimentoRepository dipartimentoRepository;

    @Autowired
    DipartimentoMapper dipartimentoMapper;

    @Autowired
    PosizioneLavorativaService posizioneLavorativaService;

    public void insertDipartimento(DipartimentoRequestInsert dipartimentoRequestInsert){
        Dipartimento dipartimento = dipartimentoMapper.fromDipartimentoRequestInsert(dipartimentoRequestInsert);
        dipartimentoRepository.save(dipartimento);
    }

    public Dipartimento getDipartimentoById(Long id_dipartimento){
        return dipartimentoRepository.findById(id_dipartimento)
                .orElseThrow(() -> new EntityNotFoundException("Dipartimento con id " + id_dipartimento + " non trovato"));
    }

    public Dipartimento updateDipartimentoById(Long id_dipartimento, DipartimentoRequestUpdate dipartimentoRequestUpdate){
        Dipartimento dipartimento = dipartimentoRepository.findById(id_dipartimento)
                .orElseThrow(() -> new EntityNotFoundException("Dipartimento con id " + id_dipartimento + " non trovato"));
        dipartimento.setNome(dipartimentoRequestUpdate.nome());
        dipartimento.setDescrizione(dipartimentoRequestUpdate.descrizione());
        dipartimento.setPosizioniLavorative(dipartimentoRequestUpdate.id_posizione_lavorativa().stream().map(id -> {
            try {
                return posizioneLavorativaService.getById(id);
            } catch (EntityNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet()));
        return dipartimento;
    }

    public List<Dipartimento> getAllDipartimenti(){
        return dipartimentoRepository.findAll();
    }

    public void removeDipartimentoById(Long id_dipartimento){
        dipartimentoRepository.deleteById(id_dipartimento);
    }
}
