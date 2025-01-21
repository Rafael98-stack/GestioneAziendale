package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestRegister;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Services.DipartimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteMapper {
    @Autowired
    DipartimentoService dipartimentoService;

    public Dipendente fromDipendenteRequestRegister(DipendenteRequestRegister dipendenteRequestRegister){
        return Dipendente
        .builder()
        .email(dipendenteRequestRegister.email())
        .password(dipendenteRequestRegister.password())
        .cognome(dipendenteRequestRegister.cognome())
        .data_nascita(dipendenteRequestRegister.data_nascita())
        .nome(dipendenteRequestRegister.nome())
        .dipartimento(dipartimentoService.getDipartimentoById(dipendenteRequestRegister.id_dipartimento()))
        .luogo_nascita(dipendenteRequestRegister.luogo_nascita())
        .telefono(dipendenteRequestRegister.telefono())
        .immagine_profilo(dipendenteRequestRegister.immagine_profilo())
        .build();
    }

//    public Dipendente fromDipendenteRequestUpdate(DipendenteRequestUpdate dipendenteRequestUpdate){
//        return Dipendente
//                .builder()
//                .email(dipendenteRequestUpdate.email())
//                .password(dipendenteRequestUpdate.password())
//                .cognome(dipendenteRequestUpdate.cognome())
//                .data_nascita(dipendenteRequestUpdate.data_nascita())
//                .nome(dipendenteRequestUpdate.nome())
//                .dipartimento()
//                .luogo_nascita(dipendenteRequestUpdate.luogo_nascita())
//                .telefono(dipendenteRequestUpdate.telefono())
//                .immagine_profilo(dipendenteRequestUpdate)
//                .build();
//    }
}
