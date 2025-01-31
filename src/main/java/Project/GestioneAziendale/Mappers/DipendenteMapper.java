package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestRegister;
import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestUpdate;
import Project.GestioneAziendale.Entities.Dipartimento;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.DipartimentoNotFoundException;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.PosizioneNotFoundException;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.TimbraturaNotFoundException;
import Project.GestioneAziendale.Repositories.DipartimentoRepository;
import Project.GestioneAziendale.Repositories.PosizioneLavorativaRepository;
import Project.GestioneAziendale.Repositories.TimbraturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteMapper {

    @Autowired
    DipartimentoRepository dipartimentoRepository;

    @Autowired
    PosizioneLavorativaRepository posizioneLavorativaRepository;

    @Autowired
    TimbraturaRepository timbraturaRepository;

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
                .luogo_nascita(dipendenteRequestRegister.luogo_nascita())
                .telefono(dipendenteRequestRegister.telefono())
                .immagine_profilo(dipendenteRequestRegister.immagine_profilo())
                .build();
    }


    public Dipendente fromDipendenteRequestUpdate(DipendenteRequestUpdate dipendenteRequestUpdate){
        return Dipendente
                .builder()
                .cognome(dipendenteRequestUpdate.cognome())
                .data_nascita(dipendenteRequestUpdate.data_nascita())
                .nome(dipendenteRequestUpdate.nome())
                .dipartimento(dipartimentoRepository.findById(dipendenteRequestUpdate.id_dipartimento())
                        .orElseThrow(()-> new DipartimentoNotFoundException("Dipartimento con id " + dipendenteRequestUpdate.id_dipartimento() + " non trovato")))
                .timbratura(timbraturaRepository.findById(dipendenteRequestUpdate.id_timbratura())
                        .orElseThrow(()-> new TimbraturaNotFoundException("Timbratura con id " + dipendenteRequestUpdate.id_timbratura() + " non trovato")))
                .luogo_nascita(dipendenteRequestUpdate.luogo_nascita())
                .telefono(dipendenteRequestUpdate.telefono())
                .immagine_profilo(dipendenteRequestUpdate.immagine_profilo())
                .posizioneLavorativa(posizioneLavorativaRepository
                        .findById(dipendenteRequestUpdate.id_posizione())
                        .orElseThrow(()-> new PosizioneNotFoundException("Posizione lavorativa con id " + dipendenteRequestUpdate.id_posizione() + " non trovato")))
                .build();
    }
}
