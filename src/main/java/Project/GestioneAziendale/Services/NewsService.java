package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.NewsDtos.NewsRequest;
import Project.GestioneAziendale.Dtos.NewsDtos.NewsResponse;
import Project.GestioneAziendale.Dtos.NewsDtos.NewsUpdate;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Entities.News;
import Project.GestioneAziendale.Entities.PosizioneLavorativa;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.*;
import Project.GestioneAziendale.Mappers.NewsMapper;
import Project.GestioneAziendale.Repositories.DipendeteRepository;
import Project.GestioneAziendale.Repositories.NewsRepository;
import Project.GestioneAziendale.Repositories.PosizioneLavorativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    private final DipendeteRepository dipendeteRepository;

    private final PosizioneLavorativaRepository posizioneLavorativaRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper, DipendeteRepository dipendeteRepository, PosizioneLavorativaRepository posizioneLavorativaRepository) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.dipendeteRepository = dipendeteRepository;
        this.posizioneLavorativaRepository = posizioneLavorativaRepository;
    }

    public NewsResponse createNews(NewsRequest request) throws Exception {
        Dipendente dipendente = dipendeteRepository.findById(request.id_dipendente())
                .orElseThrow(() -> new DipendenteNotFoundException("Dipendente non esiste"));

        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository.findById(dipendente.getPosizioneLavorativa().getId())
                .orElseThrow(() -> new PosizioneNotFoundException("Posizione non esiste"));

        if (posizioneLavorativa.getNome().equalsIgnoreCase("publisher")){
            News news = newsMapper.fromNewsRequest(request);
            news.setLike(0l);;
            return NewsResponse
                    .builder()
                    .id(newsRepository.save(news).getId())
                    .build();
        } else {
            throw  new CanNotCreateException("Non puoi creare una News");
        }

    }

    public News getNewsById(Long id){
        return newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("questa news non esiste"));
    }

    public List<News> getAllNews(){
        return newsRepository.findAll();
    }

    public NewsResponse updateNews(Long id, NewsUpdate newsUpdate) throws Exception {
        Dipendente dipendente = dipendeteRepository.findById(newsUpdate.id_dipendente())
                .orElseThrow(() -> new DipendenteNotFoundException("Dipendente non esiste"));

        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository.findById(dipendente.getPosizioneLavorativa().getId())
                .orElseThrow(() -> new PosizioneNotFoundException("Posizione non esiste"));

        if (posizioneLavorativa.getNome().equalsIgnoreCase("publisher")){
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("news non esistente"));
        news.setTitolo(newsUpdate.titolo());
        news.setContenuto(newsUpdate.contenuto());
        news.setImmagine(newsUpdate.immagine());
        return NewsResponse
                .builder()
                .id( newsRepository.save(news).getId())
                .build();
        } else {
            throw  new CanNotUpdateException("Non puoi aggiornare una News");
        }

    }

    public void likeNews(Long id){
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("news non esistente"));
        news.setLike(news.getLike() + 1);
        newsRepository.save(news);
    }

    public void removeNews(Long id){
        newsRepository.deleteById(id);
    }
}
