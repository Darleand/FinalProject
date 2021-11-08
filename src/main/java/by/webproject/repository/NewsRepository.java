package by.webproject.repository;

import by.webproject.entity.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {
    @NonNull
    List<News> findAll();

    News findByTitle(String s);
}
