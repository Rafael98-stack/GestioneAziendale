package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaRequest;
import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaResponse;
import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaUpdate;
import Project.GestioneAziendale.Entities.PosizioneLavorativa;
import Project.GestioneAziendale.Mappers.PosizioneLavorativaMapper;
import Project.GestioneAziendale.Repositories.PosizioneLavorativaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PosizioneLavorativaService {
    @Autowired
    private PosizioneLavorativaRepository posizioneLavorativaRepository;
    @Autowired
    private PosizioneLavorativaMapper posizioneLavorativaMapper;
    @Autowired
    private DipartimentoService dipartimentoService;

    public PosizioneLavorativa getById(Long id){
        return  posizioneLavorativaRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("posizione non trovata"));
    }

    public List<PosizioneLavorativa> getAll(){
        return posizioneLavorativaRepository.findAll();
    }

    public PosizioneLavorativaResponse create(PosizioneLavorativaRequest request){
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaMapper.fromPosizioneLavorativaRequest(request);
        return PosizioneLavorativaResponse
                .builder()
                .id(posizioneLavorativaRepository.save(posizioneLavorativa).getId())
                .build();
    }

    public PosizioneLavorativa updatePosizioneById(Long id, PosizioneLavorativaUpdate posizioneLavorativaUpdate){
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posizione non trovata"));
        posizioneLavorativa.setNome(posizioneLavorativaUpdate.nome());
        posizioneLavorativa.setDescrizione(posizioneLavorativaUpdate.descrizione());
        posizioneLavorativa.setDipartimenti(posizioneLavorativaUpdate.dipartimenti().stream().map(id_dip -> {
            try {
                return dipartimentoService.getDipartimentoById(id_dip);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet()));

    }

    public void deleteById(Long id){
        posizioneLavorativaRepository.deleteById(id);
    }
}
