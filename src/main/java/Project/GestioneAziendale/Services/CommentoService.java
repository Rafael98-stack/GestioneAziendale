package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoInsertUpdate;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoRequestInsert;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoResponse;
import Project.GestioneAziendale.Entities.Commento;
import Project.GestioneAziendale.Mappers.CommentoMapper;
import Project.GestioneAziendale.Repositories.CommentoRepository;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentoService {


    private final CommentoRepository commentoRepository;


    private final CommentoMapper commentoMapper;


    private final DipendeteRepository dipendeteRepository;

    @Autowired
    public CommentoService(CommentoRepository commentoRepository, CommentoMapper commentoMapper, DipendeteRepository dipendeteRepository) {
        this.commentoRepository = commentoRepository;
        this.commentoMapper = commentoMapper;
        this.dipendeteRepository = dipendeteRepository;
    }

    public CommentoResponse insertCommento(CommentoRequestInsert commentoRequestInsert){
        Commento commento = commentoMapper.fromCommentoRequestInsert(commentoRequestInsert);
        return CommentoResponse
                .builder()
                .id(commentoRepository.save(commento).getId())
                .build();
    }

    public Commento getCommentoById(Long id_commento){
        return commentoRepository.findById(id_commento)
                .orElseThrow(()-> new EntityNotFoundException("Commento con id " + id_commento + " non trovato"));
    }

    public List<Commento> gtAllCommenti(){
        return commentoRepository.findAll();
    }

    public CommentoResponse updateCommentoById(Long id_commento, CommentoInsertUpdate commentoInsertUpdate){
        Commento commento = commentoRepository.findById(id_commento)
                .orElseThrow(()-> new EntityNotFoundException("Commento con id " + id_commento + " non trovato"));
        commento.setContenuto(commentoInsertUpdate.contenuto());
        commento.setDipendente(dipendeteRepository.findById(commentoInsertUpdate.id_dipendente())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente con id " + commentoInsertUpdate.id_dipendente() + " non trovato")));
        commento.setNews(commentoRepository.findById(commentoInsertUpdate.id_newse()).get().getNews());

        return CommentoResponse
                .builder()
                .id(commentoRepository.save(commento).getId())
                .build();
    }

    public void removeCommentoById(Long id_commento){
        commentoRepository.deleteById(id_commento);
    }
}
