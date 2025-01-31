package Project.GestioneAziendale.Controllers;

import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoInsertUpdate;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoRequestInsert;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoResponse;
import Project.GestioneAziendale.Entities.Commento;
import Project.GestioneAziendale.Services.CommentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/Commento")
@CrossOrigin(origins = "http://localhost:8080")
public class CommentoController {
    @Autowired
    CommentoService commentoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Commento> getById(@PathVariable Long id){
        return new ResponseEntity<>(commentoService.getCommentoById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Commento>> getAll(){
        return  new ResponseEntity<>(commentoService.gtAllCommenti(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentoResponse> create(@RequestBody @Valid CommentoRequestInsert request){
        return new ResponseEntity<>(commentoService.insertCommento(request), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<CommentoResponse> update(@PathVariable Long id, @RequestBody @Valid CommentoInsertUpdate request) {
        return new ResponseEntity<>(commentoService.updateCommentoById(id, request), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommentoResponse> deleteById(@PathVariable Long id){
        commentoService.removeCommentoById(id);
        return new ResponseEntity<>(
                new CommentoResponse(id),HttpStatus.OK);
    }
}
