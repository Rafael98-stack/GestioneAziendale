package Project.GestioneAziendale.Controllers;

import Project.GestioneAziendale.Services.ComunicazioneScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/v1/scheduled_comunicazione")
public class ComunicazioneScheduledController
{
   @Autowired
    private ComunicazioneScheduledService comunicazioneScheduledService;

   @PostMapping("/create")
   public ResponseEntity<EntityIdResponse> createComunicazioneScheduled(@RequestBody ComunicazioneScheduledRequest request) throws IllegalTransactionException, MyEntityNotFoundException, SchedulerException {
       return new ResponseEntity<>(comunicazioneScheduledService.createComunicazioneScheduled(request), HttpStatus.CREATED);
   }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> updateTransazioneScheduled(@PathVariable Long id,
                                                                       @RequestBody TransazioneScheduledUpdateRequest request) throws SchedulerException {
        return new ResponseEntity<>(transazioneScheduledService.updateTransazioneScheduled(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) throws SchedulerException {
        transazioneScheduledService.deleteTransazioneScheduledById(id);
        return new ResponseEntity<>(new GenericResponse
                ("Transazione schedulata con id " + id + " eliminata con successo"), HttpStatus.OK);
    }
}
