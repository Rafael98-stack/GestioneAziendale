package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaRequest;
import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaResponse;
import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaUpdate;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Entities.PosizioneLavorativa;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.DipartimentoNotFoundException;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.DipendenteNotFoundException;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.PosizioneNotFoundException;
import Project.GestioneAziendale.Mappers.PosizioneLavorativaMapper;
import Project.GestioneAziendale.Repositories.DipartimentoRepository;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import Project.GestioneAziendale.Repositories.PosizioneLavorativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PosizioneLavorativaService {

    private final PosizioneLavorativaRepository posizioneLavorativaRepository;

    private final PosizioneLavorativaMapper posizioneLavorativaMapper;

    private final DipartimentoRepository dipartimentoRepository;

    private final DipendeteRepository dipendeteRepository;

    @Autowired
    public PosizioneLavorativaService(PosizioneLavorativaRepository posizioneLavorativaRepository, PosizioneLavorativaMapper posizioneLavorativaMapper, DipartimentoRepository dipartimentoRepository, DipendeteRepository dipendeteRepository) {
        this.posizioneLavorativaRepository = posizioneLavorativaRepository;
        this.posizioneLavorativaMapper = posizioneLavorativaMapper;
        this.dipartimentoRepository = dipartimentoRepository;
        this.dipendeteRepository = dipendeteRepository;
    }



    public PosizioneLavorativa getById(Long id){
        return  posizioneLavorativaRepository
                .findById(id)
                .orElseThrow(() -> new PosizioneNotFoundException("posizione non trovata"));
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

    public PosizioneLavorativaResponse updatePosizioneById(Long id, PosizioneLavorativaUpdate posizioneLavorativaUpdate){
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository.findById(id)
                .orElseThrow(() -> new PosizioneNotFoundException("Posizione non trovata"));
        if (posizioneLavorativaUpdate.id_dipendente() == null &&
                posizioneLavorativaUpdate.dipartimenti() == null){
            posizioneLavorativa.setNome(posizioneLavorativaUpdate.nome());
            posizioneLavorativa.setDescrizione(posizioneLavorativaUpdate.descrizione());
            return PosizioneLavorativaResponse
                    .builder()
                    .id(posizioneLavorativaRepository.save(posizioneLavorativa).getId())
                    .build();
        }
        Dipendente dipendente = dipendeteRepository.findById(posizioneLavorativaUpdate.id_dipendente())
                .orElseThrow(() ->  new DipendenteNotFoundException("Dipendente non trovato"));

        dipendente.setPosizioneLavorativa(posizioneLavorativa);
        dipendeteRepository.save(dipendente);

        posizioneLavorativa.setNome(posizioneLavorativaUpdate.nome());
        posizioneLavorativa.setDescrizione(posizioneLavorativaUpdate.descrizione());
        posizioneLavorativa.setDipartimenti(posizioneLavorativaUpdate.dipartimenti().stream().map(id_dip -> {
                return dipartimentoRepository.findById(id_dip)
                        .orElseThrow(() ->  new DipartimentoNotFoundException("Dipartimento non trovato"));
        }).collect(Collectors.toSet()));

        return PosizioneLavorativaResponse
                .builder()
                .id(posizioneLavorativaRepository.save(posizioneLavorativa).getId())
                .build();
    }

    public void deleteById(Long id){
        posizioneLavorativaRepository.deleteById(id);
    }
}
