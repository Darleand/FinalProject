package by.webproject.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

public class NewsDTO {
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String announce;
    @NotBlank
    private String content;
    private ZonedDateTime create_date;
    private ZonedDateTime publish_date;

    public ZonedDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(ZonedDateTime create_date) {
        this.create_date = create_date;
    }

    public ZonedDateTime getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(ZonedDateTime publish_date) {
        this.publish_date = publish_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
