package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestRegister;
import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestUpdate;
import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaResponse;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Entities.Timbratura;
import Project.GestioneAziendale.Mappers.TimbraturaMapper;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import Project.GestioneAziendale.Repositories.TimbraturaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    @Autowired
    private DipendeteRepository dipendeteRepository;

    public TimbraturaResponse registerTimbratura(TimbraturaRequestRegister timbraturaRequestRegister)
    {
        Timbratura timbratura = timbraturaMapper.fromTimbraturaRequestRegister(timbraturaRequestRegister);

        return TimbraturaResponse
                .builder()
                .id(timbraturaRepository.save(timbratura).getId())
                .build();
    }

    public Timbratura getTimbraturaById(Long idTimbratura)
    {
        return timbraturaRepository.
                findById(idTimbratura)
                .orElseThrow(() -> new EntityNotFoundException("Timbratura con ID " + idTimbratura + " non trovata"));
    }

    public List<Timbratura> getAllTimbratura()
    {
        return timbraturaRepository.findAll();
    }

    public TimbraturaResponse updateTimbraturaById(Long idTimbratura, TimbraturaRequestUpdate timbraturaRequestUpdate,Integer scelta)
    {
        // Recupera la timbratura esistente o lancia un'eccezione se non trovata
        Timbratura timbratura = timbraturaRepository.findById(idTimbratura)
                .orElseThrow(() -> new EntityNotFoundException("Timbratura con ID " + idTimbratura + " non trovata"));

        // Aggiorna i campi della timbratura in base all'input
        switch (scelta){
            case 1:
                timbratura.setOrario_entrata(LocalDateTime.now());
                break;
            case 2:
                timbratura.setInizio_pranzo(LocalDateTime.now());
                break;
            case 3:
                timbratura.setFine_pranzo(LocalDateTime.now());
                break;
            case 4:
                timbratura.setUscita(LocalDateTime.now());
                break;
            default:
                System.out.println("Nessuna scelta impostata");
        }





        if (timbratura.getDipendente() !=null)
        {
            // Aggiorna il dipendente associato (opzionale)
            Dipendente dipendente = dipendeteRepository.findById(timbratura.getDipendente().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Dipendente con ID " + timbratura.getDipendente() + " non trovato"));
            timbratura.setDipendente(dipendente);
        }

        // Salva la timbratura aggiornata
        return TimbraturaResponse
                .builder()
                .id(timbraturaRepository.save(timbratura).getId())
                .build();
    }


    public void removeTimbraturaById(Long idTimbratura){timbraturaRepository.deleteById(idTimbratura);}

    }

