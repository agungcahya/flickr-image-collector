package com.spring.project.flickrimagecollector.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.project.flickrimagecollector.service.FlickrService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flickr-feed")
public class FlickrController {

    @Autowired
    private FlickrService flickrService;

    @GetMapping("/pull")
    public ResponseEntity<String> pullFeed() throws ParseException {
        return ResponseEntity.ok(flickrService.pullFeeds());
    }
}
