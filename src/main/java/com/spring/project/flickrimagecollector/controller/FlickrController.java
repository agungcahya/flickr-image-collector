package com.spring.project.flickrimagecollector.controller;

import com.spring.project.flickrimagecollector.entity.FeedEntity;
import com.spring.project.flickrimagecollector.service.FlickrService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/flickr-feed")
public class FlickrController {

    @Autowired
    private FlickrService flickrService;

    @PostMapping("/pull")
    public ResponseEntity<List<FeedEntity>> pullFeed(@RequestParam @Nullable String id, String ids, String tags, String tagmode) throws ParseException {
        return ResponseEntity.ok(flickrService.pullFeeds(id, ids, tags, tagmode));
    }

    @GetMapping("/author")
    public ResponseEntity getFeedByAuthorId(@RequestParam String authorId) {
        if (flickrService.getByAuhtorId(authorId).isPresent()) {
            return ResponseEntity.ok(flickrService.getByAuhtorId(authorId).get());
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/feeds")
    public ResponseEntity getListFeed(@RequestParam @Nullable String author, String authorId, String title, String tags) {
        List<FeedEntity> feedEntityList = flickrService.getListFeed(author, authorId, title, tags);
        if (feedEntityList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(feedEntityList);
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity deleteAllFeed() {
        return ResponseEntity.ok(flickrService.deleteAllFeed());
    }

}
