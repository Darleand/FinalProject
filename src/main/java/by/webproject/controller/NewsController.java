package by.webproject.controller;

import by.webproject.dto.NewsDTO;
import by.webproject.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getNewsForId(@PathVariable Long id){
        NewsDTO newsDTO = newsService.getNewsForId(id);
        if(newsDTO == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newsDTO);
    }
}
