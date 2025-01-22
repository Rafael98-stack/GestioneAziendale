package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestRegister;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Entities.Timbratura;
import Project.GestioneAziendale.Mappers.TimbraturaMapper;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import Project.GestioneAziendale.Repositories.TimbraturaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimbraturaService
{
    @Autowired
    private TimbraturaRepository timbraturaRepository;
    @Autowired
    private TimbraturaMapper timbraturaMapper;
    @Autowired
    private DipendenteService dipendenteService;

    public void registerTimbratura(DipendenteRequestRegister dipendenteRequestRegister) {
        Timbratura timbratura = timbraturaMapper.fromDipendenteRequestRegister(dipendenteRequestRegister);
        timbraturaRepository.save(timbratura);
    }

    public Timbratura getTimbraturaById(Long idTimbratura) {
        return timbraturaRepository.
                findById(idTimbratura)
                .orElseThrow(() -> new EntityNotFoundException("Timbratura con ID " + idTimbratura + " non trovata"));
    }

    public List<Timbratura> getAllTimbratura() {
        return timbraturaRepository.findAll();
    }

    public <TimbraturaRequestUpdate> Timbratura updateTimbraturaById(Long idTimbratura, TimbraturaRequestUpdate timbraturaRequestUpdate) {
        // Recupera la timbratura esistente o lancia un'eccezione se non trovata
        Timbratura timbratura = timbraturaRepository.findById(idTimbratura)
                .orElseThrow(() -> new EntityNotFoundException("Timbratura con ID " + idTimbratura + " non trovata"));

        // Aggiorna i campi della timbratura in base all'input
        timbratura.setOrario_entrata(timbraturaRequestUpdate.getOrario_entrata());
        timbratura.setInizio_pranzo(timbraturaRequestUpdate.getInizio_pranzo());
        timbratura.setFine_pranzo(timbraturaRequestUpdate.getFine_pranzo());
        timbratura.setUscita(timbraturaRequestUpdate.getUscita());


        if (timbraturaRequestUpdate.getDipendenteById() != null) {
            // Aggiorna il dipendente associato (opzionale)
            Dipendente dipendente = DipendeteRepository.findById(timbraturaRequestUpdate.getDipendenteById())
                    .orElseThrow(() -> new EntityNotFoundException("Dipendente con ID " + timbraturaRequestUpdate.getDipendenteById() + " non trovato"));
            timbratura.setDipendente(dipendente);
        }

        // Salva la timbratura aggiornata
        return timbraturaRepository.save(timbratura);
    }

    public void removeTimbraturaById(Long idTimbratura){timbraturaRepository.deleteById(idTimbratura);}

    }

}
