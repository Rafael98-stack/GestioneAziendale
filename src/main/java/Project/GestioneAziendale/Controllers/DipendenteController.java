package Project.GestioneAziendale.Controllers;

import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestRegister;
import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestUpdate;
import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteResponse;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Services.DipendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/Dipendente")
@CrossOrigin(origins = "http://localhost:4200")
public class DipendenteController {

    @Autowired
    DipendenteService dipendenteService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Dipendente> getById(@PathVariable Long id){
        return new ResponseEntity<>(dipendenteService.getDipendenteById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Dipendente>> getAll(){
        return  new ResponseEntity<>(dipendenteService.getAllDipendenti(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DipendenteResponse> create(@RequestBody @Valid DipendenteRequestRegister request){
        return new ResponseEntity<>(dipendenteService.registerDipendente(request), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<DipendenteResponse> update(@PathVariable Long id, @RequestBody @Valid DipendenteRequestUpdate request) {
        return new ResponseEntity<>(dipendenteService.updateDipendeteById(id, request), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DipendenteResponse> deleteById(@PathVariable Long id){
        dipendenteService.removeDipendenteById(id);
        return new ResponseEntity<>(
                new DipendenteResponse(id),HttpStatus.OK);
    }

}
