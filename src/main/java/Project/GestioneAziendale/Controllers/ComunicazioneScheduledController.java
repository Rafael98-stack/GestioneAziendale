package Project.GestioneAziendale.Controllers;

import Project.GestioneAziendale.Dtos.ComunicazioneScheduledDtos.ComunicazioneScheduledRequest;
import Project.GestioneAziendale.Dtos.ComunicazioneScheduledDtos.ComunicazioneScheduledUpdateRequest;
import Project.GestioneAziendale.Dtos.ComunicazioneScheduledDtos.EntityIdResponse;
import Project.GestioneAziendale.Dtos.ComunicazioneScheduledDtos.GenericResponse;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.IllegalTransactionException;
import Project.GestioneAziendale.Services.ComunicazioneScheduledService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.quartz.SchedulerException;

@RestController
@RequestMapping("/app/v1/scheduled_comunicazione")
public class ComunicazioneScheduledController
{
   @Autowired
    private ComunicazioneScheduledService comunicazioneScheduledService;

   @PostMapping("/create")
   public ResponseEntity<EntityIdResponse> createComunicazioneScheduled(@RequestBody ComunicazioneScheduledRequest request) throws IllegalTransactionException, EntityNotFoundException, SchedulerException {
       return new ResponseEntity<>(comunicazioneScheduledService.createComunicazioneScheduled(request), HttpStatus.CREATED);
   }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> updateComunicazioneScheduled(@PathVariable Long id,
                                                                       @RequestBody ComunicazioneScheduledUpdateRequest request) throws SchedulerException {
        return new ResponseEntity<>(comunicazioneScheduledService.updateComunicazioneScheduled(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) throws SchedulerException {
        comunicazioneScheduledService.deleteComunicazioneScheduledById(id);
        return new ResponseEntity<>(new GenericResponse
                ("Comunicazione schedulata con id " + id + " eliminata con successo"), HttpStatus.OK);
    }
}
