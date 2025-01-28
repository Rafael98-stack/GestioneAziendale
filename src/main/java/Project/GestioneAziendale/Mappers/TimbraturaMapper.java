package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestRegister;
import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestUpdate;
import Project.GestioneAziendale.Entities.Timbratura;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.CanNotUpdateException;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.TimbraturaNotFoundException;
import Project.GestioneAziendale.Repositories.TimbraturaRepository;
import Project.GestioneAziendale.Services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TimbraturaMapper {
    @Autowired
    DipendenteService dipendenteService;
    @Autowired
    TimbraturaRepository timbraturaRepository;

    public Timbratura fromTimbraturaRequestRegister(TimbraturaRequestRegister timbraturaRequestRegister) {
        return Timbratura
                .builder()
                .data_corrente(LocalDate.now())
                .build();
    }

    public Timbratura fromTimbraturaRequestUpdate(TimbraturaRequestUpdate timbraturaRequestUpdate) throws Exception {
        // Aggiorna i campi della timbratura in base all'input
        Timbratura timbratura = timbraturaRepository.findById(timbraturaRequestUpdate.id_timbratura().longValue())
                .orElseThrow(() -> new TimbraturaNotFoundException("Timbratura con ID " + timbraturaRequestUpdate.id_timbratura() + " non trovata"));

        switch (timbraturaRequestUpdate.numero_scelta()) {
            case 1:
                if (timbratura.getOrario_entrata() != null) {
                    throw new CanNotUpdateException("Orario entrata già esistente");
                }
            timbratura.setOrario_entrata(LocalDateTime.now());
                return timbratura;
            case 2:
                if (timbratura.getInizio_pranzo() != null) {
                    throw new CanNotUpdateException("Inizio pranzo già esistente");
                }
                timbratura.setInizio_pranzo(LocalDateTime.now());
                return timbratura;
                case 3:
                if (timbratura.getFine_pranzo() != null) {
                    throw new CanNotUpdateException("Fine pranzo già esistente");
                }
                timbratura.setFine_pranzo(LocalDateTime.now());
                return timbratura;
                case 4:
                if (timbratura.getUscita() != null) {
                throw new CanNotUpdateException("Uscita già esistente");
                }
                timbratura.setUscita(LocalDateTime.now());
                    return timbratura;
                    default:
                System.out.println("Nessuna scelta impostata o Non valida. Sceglielte 1-4");
        }
        return null;
    }
}
