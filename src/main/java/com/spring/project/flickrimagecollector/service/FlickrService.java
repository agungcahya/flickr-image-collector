package com.spring.project.flickrimagecollector.service;

import com.spring.project.flickrimagecollector.entity.FeedEntity;
import com.spring.project.flickrimagecollector.repository.FeedRepository;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONObject;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class FlickrService {

    @Autowired
    private FeedRepository feedRepository;

    private JSONParser jsonParser = new JSONParser();

    public List<FeedEntity> pullFeeds(String id, String ids, String tags, String tagmode) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        List<FeedEntity> feedEntityList = new ArrayList<>();
        String flickrFeedUrl = "https://www.flickr.com/services/feeds/photos_public.gne";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(flickrFeedUrl)
                .queryParam("id", id)
                .queryParam("ids", ids)
                .queryParam("tags", tags)
                .queryParam("tagmode", tagmode)
                .queryParam("format", "json")
                .queryParam("nojsoncallback", 1);
        ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
        JSONArray feedJson = (JSONArray) jsonObject.get("items");

        feedJson.forEach(feed -> {
            JSONObject feedObject = (JSONObject) feed;
            feedEntityList.add(FeedEntity.builder()
                    .author(feedObject.get("author").toString())
                    .authorId(feedObject.get("author_id").toString())
                    .published(ZonedDateTime.parse(feedObject.get("published").toString()))
                    .dateTaken(ZonedDateTime.parse(feedObject.get("date_taken").toString()))
                    .description(feedObject.get("description").toString())
                    .link(feedObject.get("link").toString())
                    .media(feedObject.get("media").toString())
                    .tags(feedObject.get("tags").toString())
                    .title(feedObject.get("title").toString())
                    .build());
        });
        return feedRepository.saveAll(feedEntityList);
    }

    public FeedEntity getByAuhtorId(String authorId) {
        Optional<FeedEntity> feedEntity = feedRepository.findByAuthorId(authorId);
        return feedEntity.orElse(null);
    }

    public List<FeedEntity> getListFeed(String author, String authorId, String title, String tags) {
        List<FeedEntity> feedEntityList;
        feedEntityList = feedRepository.getListFeed(author, authorId, title, tags);
        return feedEntityList;
    }

    public String deleteAllFeed() {
        feedRepository.deleteAll();
        if(feedRepository.findAll().isEmpty()) {
            return "Deleted";
        } else {
            return "Deleted Failed";
        }
    }
}
