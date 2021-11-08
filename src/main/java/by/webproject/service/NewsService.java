package by.webproject.service;

import by.webproject.dto.NewsDTO;
import by.webproject.repository.NewsRepository;
import by.webproject.service.converter.NewsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsService implements NewsServiceInter{
    private NewsRepository newsRepository;
    private final NewsConverter newsConverter;

    @Autowired
    public NewsService(NewsConverter newsConverter, NewsRepository newsRepository) {
        this.newsConverter = newsConverter;
        this.newsRepository = newsRepository;
    }

    public NewsDTO getNewsForId(long id) {
        return newsConverter.toDTO(newsRepository.findById(id).orElse(null));
    }

    public List<NewsDTO> getAllNews(){
        return newsConverter.toDTO(newsRepository.findAll());
    }

    public void addNews(NewsDTO newsDTO){
        newsRepository.save(newsConverter.toEntity(newsDTO));
    }

    public NewsDTO getNewsForName(String s){
        return newsConverter.toDTO(newsRepository.findByTitle(s));
    }

}
