package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaRequest;
import Project.GestioneAziendale.Entities.PosizioneLavorativa;
import Project.GestioneAziendale.Repositories.DipartimentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PosizioneLavorativaMapper {

    @Autowired
    DipartimentoRepository dipartimentoRepository;

    public PosizioneLavorativa fromPosizioneLavorativaRequest(PosizioneLavorativaRequest request){
        return PosizioneLavorativa
                .builder()
                .nome(request.nome())
                .descrizione(request.descrizione())
                .dipartimenti(request.dipartimenti().stream().map(id ->{
                    try {
                        return dipartimentoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Dipartimenti non trovati"));
                    } catch (RuntimeException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toSet()))
                .build();
    }
}
