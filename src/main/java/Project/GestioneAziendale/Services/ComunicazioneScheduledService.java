package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleRequest;
import Project.GestioneAziendale.Dtos.ComunicazioneScheduledDtos.ComunicazioneScheduledRequest;
import Project.GestioneAziendale.Entities.ComunicazioneScheduled;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.IllegalTransactionException;
import Project.GestioneAziendale.Repositories.ComunicazioneAziendaleRepository;
import Project.GestioneAziendale.Repositories.ComunicazioneScheduledRepository;
import jakarta.persistence.EntityNotFoundException;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

@Service
public class ComunicazioneScheduledService implements Job {

    @Autowired
    private ComunicazioneScheduledRepository comunicazioneScheduledRepository;
    @Autowired
    private ComunicazioneScheduled comunicazioneScheduled;
    @Autowired
    private Dipendente dipendente;
    @Autowired
    private ComunicazioneAziendaleRepository comunicazioneAziendaleRepository;
    @Autowired
    private ComunicazioneAziendaleService comunicazioneAziendaleService;
    @Autowired
    private DipendenteService dipendenteService;

    public EntityResponse createComunicazioneScheduled(ComunicazioneScheduledRequest request)
            throws EntityNotFoundException, IllegalTransactionException, SchedulerException
    {

    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        ComunicazioneAziendaleRequest request = (ComunicazioneAziendaleRequest) jobDataMap.get("entityData");
        Long id_scheduled = jobDataMap.getLongValue("id");
        try {
            ComunicazioneAziendaleService.insertComunicazione(request);
        } catch (EntityNotFoundException | IllegalTransactionException e) {
            throw new RuntimeException(e);
        comunicazioneAziendaleRepository.deleteById(id_scheduled);
    }
}
