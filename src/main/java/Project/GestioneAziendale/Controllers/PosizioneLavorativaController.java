 package Project.GestioneAziendale.Controllers;

 import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaRequest;
 import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaResponse;
 import Project.GestioneAziendale.Dtos.PosizioneLavorativaDtos.PosizioneLavorativaUpdate;
 import Project.GestioneAziendale.Entities.PosizioneLavorativa;
 import Project.GestioneAziendale.Services.PosizioneLavorativaService;
 import jakarta.validation.Valid;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

@RestController
@RequestMapping("/app/v1/posizionelavorativa")
@CrossOrigin(origins = "http://localhost:4200")
public class PosizioneLavorativaController {
    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;

    @GetMapping("/get/{id}")
    public ResponseEntity<PosizioneLavorativa> getById(@PathVariable Long id){
        return new ResponseEntity<>(posizioneLavorativaService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PosizioneLavorativa>> getAll(){
        return  new ResponseEntity<>(posizioneLavorativaService.getAll(),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PosizioneLavorativaResponse> update(@PathVariable Long id, @RequestBody @Valid PosizioneLavorativaUpdate request) {
        return new ResponseEntity<>(posizioneLavorativaService.updatePosizioneById(id, request), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<PosizioneLavorativaResponse> create(@RequestBody @Valid PosizioneLavorativaRequest request){
        return new ResponseEntity<>(posizioneLavorativaService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PosizioneLavorativaResponse> deleteById(@PathVariable Long id){
        posizioneLavorativaService.deleteById(id);
        return new ResponseEntity<>(
                new PosizioneLavorativaResponse(id),HttpStatus.OK);
    }

}
