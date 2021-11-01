package by.webproject.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.time.ZonedDateTime;

@Component
public class NewsDTO {
    private Long id;
    private String title;
    private String announce;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}