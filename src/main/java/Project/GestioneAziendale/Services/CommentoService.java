package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoInsertUpdate;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoRequestInsert;
import Project.GestioneAziendale.Entities.Commento;
import Project.GestioneAziendale.Mappers.CommentoMapper;
import Project.GestioneAziendale.Repositories.CommentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentoService {

    @Autowired
    CommentoRepository commentoRepository;

    @Autowired
    CommentoMapper commentoMapper;

    @Autowired
    DipendenteService dipendenteService;

    public void insertCommento(CommentoRequestInsert commentoRequestInsert){
        Commento commento = commentoMapper.fromCommentoRequestInsert(commentoRequestInsert);
        commentoRepository.save(commento);
    }

    public Commento getCommentoById(Long id_commento){
        return commentoRepository.findById(id_commento)
                .orElseThrow(()-> new EntityNotFoundException("Commento con id " + id_commento + " non trovato"));
    }

    public List<Commento> gtAllCommenti(){
        return commentoRepository.findAll();
    }

    public Commento updateCommentoById(Long id_commento, CommentoInsertUpdate commentoInsertUpdate){
        Commento commento = commentoRepository.findById(id_commento)
                .orElseThrow(()-> new EntityNotFoundException("Commento con id " + id_commento + " non trovato"));
        commento.setContenuto(commentoInsertUpdate.contenuto());
        commento.setDipendente(dipendenteService.getDipendenteById(commentoInsertUpdate.id_dipendente()));
        commento.setNewses();

        return commentoRepository.save(commento);
    }

    public void removeCommentoById(Long id_commento){
        commentoRepository.deleteById(id_commento);
    }
}
