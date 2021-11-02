package by.webproject.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String announce;

    @Column
    private ZonedDateTime create_date = ZonedDateTime.now();

    @Column
    private String content;

    @Column
    private ZonedDateTime publish_date;

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

    public ZonedDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(ZonedDateTime create_date) {
        this.create_date = create_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(ZonedDateTime publish_date) {
        this.publish_date = publish_date;
    }

}