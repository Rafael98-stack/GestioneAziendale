package Project.GestioneAziendale.Controllers;

import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleRequest;
import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleResponse;
import Project.GestioneAziendale.Dtos.ComunicazioneAziendaleDtos.ComunicazioneAziendaleUpdate;
import Project.GestioneAziendale.Entities.ComunicazioneAziendale;
import Project.GestioneAziendale.Services.ComunicazioneAziendaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/ComunicazioneAziendale")
@CrossOrigin(origins = "http://localhost:4200")
public class ComunicazioneAziendaleController {
    @Autowired
ComunicazioneAziendaleService comunicazioneAziendaleService;

    @GetMapping("/get/{id}")
public ResponseEntity<ComunicazioneAziendale> getById(@PathVariable Long id){
    return new ResponseEntity<>(comunicazioneAziendaleService.getComunicazioneById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
public ResponseEntity<List<ComunicazioneAziendale>> getAll(){
    return  new ResponseEntity<>(comunicazioneAziendaleService.getAllComunicazioni(),HttpStatus.OK);
    }

    @PostMapping("/create")
public ResponseEntity<ComunicazioneAziendaleResponse> create(@RequestBody @Valid ComunicazioneAziendaleRequest request) throws Exception {
    return new ResponseEntity<>(comunicazioneAziendaleService.insertComunicazione(request), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
public ResponseEntity<ComunicazioneAziendaleResponse> update(@PathVariable Long id, @RequestBody @Valid ComunicazioneAziendaleUpdate request) throws Exception {
    return new ResponseEntity<>(comunicazioneAziendaleService.updateComunicazioneById(id, request), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
public ResponseEntity<ComunicazioneAziendaleResponse> deleteById(@PathVariable Long id){
comunicazioneAziendaleService.removeComunicazioneById(id);
        return new ResponseEntity<>(
            new ComunicazioneAziendaleResponse(id),HttpStatus.OK);
    }
}
