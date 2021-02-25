package com.spring.project.flickrimagecollector.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Builder
@Setter
@Getter
@Entity
@Table(name = "feed", schema = "public")
public class FeedEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "link")
    private String link;
    @Column(name = "media")
    private String media;
    @Column(name = "date_taken")
    private ZonedDateTime dateTaken;
    @Column(name = "description")
    private String description;
    @Column(name = "published")
    private ZonedDateTime published;
    @Column(name = "author")
    private String author;
    @Column(name = "author_id")
    private String authorId;
    @Column(name = "tags")
    private String tags;

}
