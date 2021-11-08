package by.webproject.service.converter;

import by.webproject.dto.NewsDTO;
import by.webproject.entity.News;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class NewsConverter implements Converter<News, NewsDTO> {

    @Override
    public News toEntity(NewsDTO dto) {
        News news = new News();
        news.setContent(dto.getContent());
        news.setId(dto.getId());
        news.setAnnounce(dto.getAnnounce());
        news.setTitle(dto.getTitle());
        return news;
    }

    @Override
    public List<News> toEntity(List<NewsDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public NewsDTO toDTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setContent(news.getContent());
        newsDTO.setAnnounce(news.getAnnounce());
        return newsDTO;
    }

    @Override
    public List<NewsDTO> toDTO(List<News> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
