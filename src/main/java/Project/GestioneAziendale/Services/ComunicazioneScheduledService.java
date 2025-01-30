package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleRequest;
import Project.GestioneAziendale.Dtos.ComunicazioneScheduledDtos.ComunicazioneScheduledRequest;
import Project.GestioneAziendale.Dtos.ComunicazioneScheduledDtos.ComunicazioneScheduledUpdateRequest;
import Project.GestioneAziendale.Dtos.ComunicazioneScheduledDtos.EntityIdResponse;
import Project.GestioneAziendale.Entities.ComunicazioneScheduled;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.ComunicazioneNotFoundException;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.IllegalTransactionException;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.MyEntityNotFoundException;
import Project.GestioneAziendale.Repositories.ComunicazioneAziendaleRepository;
import Project.GestioneAziendale.Repositories.ComunicazioneScheduledRepository;
import jakarta.persistence.EntityNotFoundException;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ComunicazioneScheduledService implements Job {

    private final Scheduler scheduler;
    private final ComunicazioneScheduledRepository comunicazioneScheduledRepository;
    private final ComunicazioneAziendaleService comunicazioneAziendaleService;
    private final DipendenteService dipendenteService;

    public ComunicazioneScheduledService(Scheduler scheduler, ComunicazioneScheduledRepository comunicazioneScheduledRepository, ComunicazioneAziendaleService comunicazioneAziendaleService, DipendenteService dipendenteService) {
        this.scheduler = scheduler;
        this.comunicazioneScheduledRepository = comunicazioneScheduledRepository;
        this.comunicazioneAziendaleService = comunicazioneAziendaleService;
        this.dipendenteService = dipendenteService;
    }

    public ComunicazioneScheduled getById(Long id) throws MyEntityNotFoundException {
        return comunicazioneScheduledRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException("Comunicazione con id " + id + " non trovata"));
    }

    public List<ComunicazioneScheduled> getAllByIdDipendente(Long id){
        List<ComunicazioneScheduled> comunicazioni = comunicazioneScheduledRepository.findAll().stream()
                .filter(c -> Objects.equals(c.getDipendente().getId(), id))
                .collect(Collectors.toList());;

        if (comunicazioni.isEmpty()) {
            throw new ComunicazioneNotFoundException("Nessuna comunicazione trovata per il dipendente con ID: " + id);
        }

        return comunicazioni;
    }

    public List<ComunicazioneScheduled> getAll(){
        return comunicazioneScheduledRepository.findAll();
    }

    public EntityIdResponse createComunicazioneScheduled(ComunicazioneScheduledRequest request) throws MyEntityNotFoundException, SchedulerException {


        ComunicazioneScheduled comunicazioneScheduled = ComunicazioneScheduled
                .builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .publishTime(request.publishTime())
                .dipendente(dipendenteService.getDipendenteById(request.id_dipendente()))
                .build();

        comunicazioneScheduledRepository.save(comunicazioneScheduled);

        ComunicazioneAziendaleRequest comunicazioneAziendaleRequest = ComunicazioneAziendaleRequest
                .builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .id_dipendente(request.id_dipendente())
                .build();
        JobDetail jobDetail = buildJobDetail(comunicazioneScheduled, comunicazioneAziendaleRequest);
        org.quartz.Trigger trigger = buildJobTrigger(jobDetail, Date.from(comunicazioneScheduled.getPublishTime().atZone(ZoneId.systemDefault()).toInstant()));
        scheduler.scheduleJob(jobDetail, trigger);
        return EntityIdResponse.builder().id(comunicazioneScheduled.getId()).build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, Date publishTime) {

        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .startAt(publishTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();

    }

    private JobDetail buildJobDetail(ComunicazioneScheduled comunicazioneScheduled,
                                     ComunicazioneAziendaleRequest comunicazioneAziendaleRequest) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("entityData", comunicazioneAziendaleRequest); // ---> l'entità che passerò all'execute
        jobDataMap.put("id", comunicazioneScheduled.getId()); // ---> l'id del job
        return JobBuilder
                .newJob(ComunicazioneScheduledService.class)
                .withIdentity(String.valueOf(comunicazioneScheduled.getId()), "comunicazioni")
                .storeDurably()
                .setJobData(jobDataMap)
                .build();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        ComunicazioneAziendaleRequest request = (ComunicazioneAziendaleRequest)jobDataMap.get("entityData");
        Long id_scheduled = jobDataMap.getLongValue("id");
        try {
            comunicazioneAziendaleService.insertComunicazione(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        comunicazioneScheduledRepository.deleteById(id_scheduled);
    }

    public EntityIdResponse updateComunicazioneScheduled(Long id, ComunicazioneScheduledUpdateRequest request) throws SchedulerException, MyEntityNotFoundException {
        ComunicazioneScheduled comunicazioneScheduled = comunicazioneScheduledRepository
                .findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("La comunicazione schedulata con " + id + " non è presente"));
        JobKey jobKey = new JobKey(String.valueOf(comunicazioneScheduled.getId()), "comunicazioni");
        scheduler.deleteJob(jobKey);
        ComunicazioneScheduledRequest comunicazioneScheduledRequest = ComunicazioneScheduledRequest
                .builder()
                .titolo(request.titolo() == null ? comunicazioneScheduled.getTitolo() : request.titolo())
                .contenuto(request.contenuto() == null ? comunicazioneScheduled.getContenuto() : request.contenuto())
                .publishTime(request.publishTime() == null ? comunicazioneScheduled.getPublishTime() : request.publishTime())
                .build();
        comunicazioneScheduledRepository.deleteById(id);
        return createComunicazioneScheduled(comunicazioneScheduledRequest);
    }

    public void deleteComunicazioneScheduledById(Long id) throws SchedulerException, MyEntityNotFoundException {
        ComunicazioneScheduled comunicazioneScheduled = comunicazioneScheduledRepository
                .findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("La comunicazione schedulata con " + id + " non è presente"));
        JobKey jobKey = new JobKey(String.valueOf(comunicazioneScheduled.getId()), "comunicazioni");
        scheduler.deleteJob(jobKey);
        comunicazioneScheduledRepository.deleteById(id);
    }
}
