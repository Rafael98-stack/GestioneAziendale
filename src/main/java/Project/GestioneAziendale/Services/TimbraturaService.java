package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestRegister;
import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestUpdate;
import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaResponse;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Entities.Timbratura;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.DipendenteNotFoundException;
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

public TimbraturaResponse updateTimbraturaById(TimbraturaRequestUpdate timbraturaRequestUpdate) throws Exception {
        // Recupera la timbratura esistente o lancia un'eccezione se non trovata
    Timbratura timbratura = timbraturaMapper.fromTimbraturaRequestUpdate(timbraturaRequestUpdate);
    Dipendente dipendente = dipendeteRepository.findById(timbraturaRequestUpdate.id_dipendente())
            .orElseThrow(() -> new DipendenteNotFoundException("Dipendente con ID " + timbraturaRequestUpdate.id_dipendente() + " non trovata"));
    dipendente.setTimbratura(timbratura);
    // Salva la timbratura aggiornata
        return TimbraturaResponse
                .builder()
                .id(timbraturaRepository.save(timbratura).getId())
                .build();
    }


    public void removeTimbraturaById(Long idTimbratura){timbraturaRepository.deleteById(idTimbratura);}

    }

