package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoInsertUpdate;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoRequestInsert;
import Project.GestioneAziendale.Dtos.CommentoDtos.CommentoResponse;
import Project.GestioneAziendale.Entities.Commento;
import Project.GestioneAziendale.Mappers.CommentoMapper;
import Project.GestioneAziendale.Repositories.CommentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentoService {


    private final CommentoRepository commentoRepository;


    private final CommentoMapper commentoMapper;


    private final DipendenteService dipendenteService;

    @Autowired
    @Lazy
    public CommentoService(DipendenteService dipendenteService, CommentoMapper commentoMapper, CommentoRepository commentoRepository, CommentoRepository commentoRepository1, CommentoMapper commentoMapper1, DipendenteService dipendenteService1) {
        this.commentoRepository = commentoRepository1;
        this.commentoMapper = commentoMapper1;
        this.dipendenteService = dipendenteService1;
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
        commento.setDipendente(dipendenteService.getDipendenteById(commentoInsertUpdate.id_dipendente()));
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
