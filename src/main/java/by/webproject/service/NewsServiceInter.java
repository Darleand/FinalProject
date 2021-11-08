package by.webproject.service;

import by.webproject.dto.NewsDTO;

import java.util.List;

public interface NewsServiceInter {

    NewsDTO getNewsForId(long id);

    List<NewsDTO> getAllNews();

    void addNews(NewsDTO newsDTO);

    NewsDTO getNewsForName(String s);

}
