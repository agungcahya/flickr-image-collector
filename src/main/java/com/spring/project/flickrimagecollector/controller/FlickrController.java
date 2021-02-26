package com.spring.project.flickrimagecollector.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.project.flickrimagecollector.entity.FeedEntity;
import com.spring.project.flickrimagecollector.service.FlickrService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flickr-feed")
public class FlickrController {

    @Autowired
    private FlickrService flickrService;

    @GetMapping("/pull")
    public ResponseEntity<String> pullFeed() throws ParseException {
        return ResponseEntity.ok(flickrService.pullFeeds());
    }

    @GetMapping("/author")
    public ResponseEntity getFeedByAuthorId(@RequestParam String authorId) {
        return ResponseEntity.ok(flickrService.getByAuhtorId(authorId));
    }

    @GetMapping("/feeds")
    public ResponseEntity getListFeed(@RequestParam @Nullable String author, String authorId, String title, String tags) {
        return ResponseEntity.ok(flickrService.getListFeed(author, authorId, title, tags));
    }

}
