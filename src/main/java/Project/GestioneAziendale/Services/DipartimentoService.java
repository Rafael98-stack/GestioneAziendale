package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.DipartimentoDtos.DipartimentoRequestInsert;
import Project.GestioneAziendale.Dtos.DipartimentoDtos.DipartimentoRequestUpdate;
import Project.GestioneAziendale.Dtos.DipartimentoDtos.DipartimentoResponse;
import Project.GestioneAziendale.Entities.Dipartimento;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.DipartimentoNotFoundException;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.PosizioneNotFoundException;
import Project.GestioneAziendale.Mappers.DipartimentoMapper;
import Project.GestioneAziendale.Repositories.DipartimentoRepository;
import Project.GestioneAziendale.Repositories.PosizioneLavorativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DipartimentoService {


    private final DipartimentoRepository dipartimentoRepository;

    private final DipartimentoMapper dipartimentoMapper;

    private final PosizioneLavorativaRepository posizioneLavorativaRepository;

    @Autowired
    public DipartimentoService(DipartimentoRepository dipartimentoRepository, DipartimentoMapper dipartimentoMapper, PosizioneLavorativaRepository posizioneLavorativaRepository) {
        this.dipartimentoRepository = dipartimentoRepository;
        this.dipartimentoMapper = dipartimentoMapper;
        this.posizioneLavorativaRepository = posizioneLavorativaRepository;
    }

    public DipartimentoResponse insertDipartimento(DipartimentoRequestInsert dipartimentoRequestInsert){
    Dipartimento dipartimento = dipartimentoMapper.fromDipartimentoRequestInsert(dipartimentoRequestInsert);

        return DipartimentoResponse
                .builder()
                .id(dipartimentoRepository.save(dipartimento).getId())
                .build();
    }

    public Dipartimento getDipartimentoById(Long id_dipartimento){
        return dipartimentoRepository.findById(id_dipartimento)
                .orElseThrow(() -> new DipartimentoNotFoundException("Dipartimento con id " + id_dipartimento + " non trovato"));
    }

    public DipartimentoResponse updateDipartimentoById(Long id_dipartimento, DipartimentoRequestUpdate dipartimentoRequestUpdate){
        Dipartimento dipartimento = dipartimentoRepository.findById(id_dipartimento)
                .orElseThrow(() -> new DipartimentoNotFoundException("Dipartimento con id " + id_dipartimento + " non trovato"));
        dipartimento.setNome(dipartimentoRequestUpdate.nome());
        dipartimento.setDescrizione(dipartimentoRequestUpdate.descrizione());
        dipartimento.setPosizioniLavorative(dipartimentoRequestUpdate.id_posizione_lavorativa().stream().map(id -> {
            try {
                return posizioneLavorativaRepository.findById(id)
                        .orElseThrow(() -> new PosizioneNotFoundException("Posizione con id " + dipartimentoRequestUpdate.id_posizione_lavorativa() + " non trovato"));
            } catch (PosizioneNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet()));
        return DipartimentoResponse
                .builder()
                .id(dipartimentoRepository.save(dipartimento).getId())
                .build();
    }

    public List<Dipartimento> getAllDipartimenti(){
        return dipartimentoRepository.findAll();
    }

    public void removeDipartimentoById(Long id_dipartimento){
        dipartimentoRepository.deleteById(id_dipartimento);
    }
}
