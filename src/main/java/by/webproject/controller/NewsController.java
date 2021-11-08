package by.webproject.controller;

import by.webproject.dto.NewsDTO;
import by.webproject.service.NewsService;
import by.webproject.web.NewsAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping()
    public List<NewsDTO> getAllNews(){
        return newsService.getAllNews();
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_REDACTOR')")
    public ResponseEntity<NewsDTO> addNews(@RequestBody @Valid NewsDTO newsDTO){
        if(newsService.getNewsForName(newsDTO.getTitle()) != null){
            throw new NewsAlreadyExists();
        }else {
            newsService.addNews(newsDTO);
            return ResponseEntity.ok(newsDTO);
        }
    }

}
