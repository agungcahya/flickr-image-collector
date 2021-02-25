package com.spring.project.flickrimagecollector.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlickrService {

    public String pullFeeds() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://www.flickr.com/services/feeds/photos_public.gne?format=json";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }
}
