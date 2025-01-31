package Project.GestioneAziendale.Controllers;

import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestRegister;
import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestUpdate;
import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaResponse;
import Project.GestioneAziendale.Entities.Timbratura;
import Project.GestioneAziendale.Services.TimbraturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/Timbratura")
@CrossOrigin(origins = "http://localhost:8080")
public class TimbraturaController {
    @Autowired
    TimbraturaService timbraturaService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Timbratura> getById(@PathVariable Long id){
        return new ResponseEntity<>(timbraturaService.getTimbraturaById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Timbratura>> getAll(){
        return  new ResponseEntity<>(timbraturaService.getAllTimbratura(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TimbraturaResponse> create(@RequestBody @Valid TimbraturaRequestRegister request){
        return new ResponseEntity<>(timbraturaService.registerTimbratura(request), HttpStatus.CREATED);
    }


    @PutMapping("/update")
public ResponseEntity<TimbraturaResponse> update( @RequestBody @Valid TimbraturaRequestUpdate request) throws Exception {
        return new ResponseEntity<>(timbraturaService.updateTimbraturaById(request), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TimbraturaResponse> deleteById(@PathVariable Long id){
        timbraturaService.removeTimbraturaById(id);
        return new ResponseEntity<>(
                new TimbraturaResponse(id),HttpStatus.OK);
    }
}
