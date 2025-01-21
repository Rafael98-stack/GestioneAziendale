package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.DipartimentoDtos.DipartimentoRequestInsert;
import Project.GestioneAziendale.Entities.Dipartimento;
import Project.GestioneAziendale.Entities.PosizioneLavorativa;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DipartimentoMapper {

    public Dipartimento fromDipartimentoRequestInsert(DipartimentoRequestInsert dipartimentoRequestInsert){

        return Dipartimento
                .builder()
                .nome(dipartimentoRequestInsert.nome())
                .descrizione(dipartimentoRequestInsert.descrizione())
                .posizioniLavorative()
                .build();
    }
}
