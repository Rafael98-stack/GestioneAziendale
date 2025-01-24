package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Dtos.NewsDtos.NewsRequest;
import Project.GestioneAziendale.Dtos.NewsDtos.NewsResponse;
import Project.GestioneAziendale.Dtos.NewsDtos.NewsUpdate;
import Project.GestioneAziendale.Entities.News;
import Project.GestioneAziendale.Mappers.NewsMapper;
import Project.GestioneAziendale.Repositories.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    @Autowired
    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    public NewsResponse createNews(NewsRequest request) {
        News news = newsMapper.fromNewsRequest(request);
        news.setLikes(0L);
        return NewsResponse
                .builder()
                .id(newsRepository.save(news).getId())
                .build();
    }

    public News getNewsById(Long id){
        return newsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("questa news non esiste"));
    }

    public List<News> getAllNews(){
        return newsRepository.findAll();
    }

    public NewsResponse updateNews(Long id, NewsUpdate newsUpdate){
        News news = newsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("news non esistente"));
        news.setTitolo(newsUpdate.titolo());
        news.setContenuto(newsUpdate.contenuto());
        news.setImmagine(newsUpdate.immagine());
        return NewsResponse
                .builder()
                .id( newsRepository.save(news).getId())
                .build();

    }

    public void likeNews(Long id){
        News news = newsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("news non esistente"));
        news.setLikes(news.getLikes() + 1);
        newsRepository.save(news);
    }

    public void removeNews(Long id){
        newsRepository.deleteById(id);
    }
}
