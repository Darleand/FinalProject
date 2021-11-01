package by.webproject.service;

import by.webproject.dto.NewsDTO;
import by.webproject.entity.News;
import by.webproject.repository.NewsRepository;
import by.webproject.service.converter.NewsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NewsService {
    private NewsRepository newsRepository;
    private final NewsConverter newsConverter;

    @Autowired
    public NewsService(NewsConverter newsConverter, NewsRepository newsRepository) {
        this.newsConverter = newsConverter;
        this.newsRepository = newsRepository;
    }

    public NewsDTO getNewsForId(Long id) {
        return newsConverter.toDTO(newsRepository.findById(id).orElse(new News()));
    }
}
