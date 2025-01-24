package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestRegister;
import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestUpdate;
import Project.GestioneAziendale.Entities.Dipartimento;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Repositories.DipartimentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteMapper {

    @Autowired
    DipartimentoRepository dipartimentoRepository;

    public Dipendente fromDipendenteRequestRegister(DipendenteRequestRegister dipendenteRequestRegister){
        Dipartimento dipartimento = null;
        if (dipendenteRequestRegister.id_dipartimento() != null) {
            dipartimento = dipartimentoRepository.findById(dipendenteRequestRegister.id_dipartimento()).orElse(null);
        }

        return Dipendente.builder()
                .email(dipendenteRequestRegister.email())
                .password(dipendenteRequestRegister.password())
                .cognome(dipendenteRequestRegister.cognome())
                .data_nascita(dipendenteRequestRegister.data_nascita())
                .nome(dipendenteRequestRegister.nome())
                .dipartimento(dipartimento)
                .luogo_nascita(dipendenteRequestRegister.luogo_nascita())
                .telefono(dipendenteRequestRegister.telefono())
                .immagine_profilo(dipendenteRequestRegister.immagine_profilo())
                .build();
    }


    public Dipendente fromDipendenteRequestUpdate(DipendenteRequestUpdate dipendenteRequestUpdate){
        return Dipendente
                .builder()
                .email(dipendenteRequestUpdate.email())
                .password(dipendenteRequestUpdate.password())
                .cognome(dipendenteRequestUpdate.cognome())
                .data_nascita(dipendenteRequestUpdate.data_nascita())
                .nome(dipendenteRequestUpdate.nome())
                .dipartimento(dipartimentoRepository.findById(dipendenteRequestUpdate.id_dipartimento())
                        .orElseThrow(()-> new EntityNotFoundException("Dipartimento con id " + dipendenteRequestUpdate.id_dipartimento() + " non trovato")))
                .luogo_nascita(dipendenteRequestUpdate.luogo_nascita())
                .telefono(dipendenteRequestUpdate.telefono())
                .immagine_profilo(dipendenteRequestUpdate.immagine_profilo())
                .build();
    }
}
