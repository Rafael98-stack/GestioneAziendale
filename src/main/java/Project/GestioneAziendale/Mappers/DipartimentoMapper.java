package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.DipartimentoDtos.DipartimentoRequestInsert;
import Project.GestioneAziendale.Entities.Dipartimento;
import Project.GestioneAziendale.Services.PosizioneLavorativaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DipartimentoMapper {

    @Autowired
    PosizioneLavorativaService posizioneLavorativaService;
    public Dipartimento fromDipartimentoRequestInsert(DipartimentoRequestInsert dipartimentoRequestInsert){

        return Dipartimento
                .builder()
                .nome(dipartimentoRequestInsert.nome())
                .descrizione(dipartimentoRequestInsert.descrizione())
                .posizioniLavorative(dipartimentoRequestInsert.id_posizione_lavorativa().stream().map(id -> {
                    try {
                        return posizioneLavorativaService.getById(id);
                    } catch (EntityNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toSet()))
                .build();
    }
}
