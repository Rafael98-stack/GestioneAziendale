package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaRequest;
import Project.GestioneAziendale.Entities.PosizioneLavorativa;
import Project.GestioneAziendale.Services.DipartimentoService;
import Project.GestioneAziendale.Services.PosizioneLavorativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PosizioneLavorativaMapper {
    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;

    @Autowired
    DipartimentoService dipartimentoService;

    public PosizioneLavorativa fromPosizioneLavorativaRequest(PosizioneLavorativaRequest request){
        return PosizioneLavorativa
                .builder()
                .nome(request.nome())
                .descrizione(request.descrizione())
                .dipartimenti(request.dipartimenti().stream().map(id ->{
                    try {
                        return dipartimentoService.getDipartimentoById(id);
                    } catch (RuntimeException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toSet()))
                .build();
    }
}
