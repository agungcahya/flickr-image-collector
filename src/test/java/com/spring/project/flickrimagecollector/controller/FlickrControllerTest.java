package com.spring.project.flickrimagecollector.controller;

import com.spring.project.flickrimagecollector.entity.FeedEntity;
import com.spring.project.flickrimagecollector.service.FlickrService;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class FlickrControllerTest {

    @InjectMocks
    FlickrController flickrController;

    @Mock
    FlickrService flickrService;

    @Test
    public void pullFeed() throws ParseException {
        List<FeedEntity> feedEntityList = new ArrayList<>();
        Mockito.when(flickrService.pullFeeds(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(feedEntityList);
        ResponseEntity<List<FeedEntity>> actual = flickrController.pullFeed(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Assert.assertEquals("200 OK", actual.getStatusCode().toString());
    }

    @Test
    public void getFeedByAuthorId() {
        FeedEntity feedEntity = new FeedEntity();
        Mockito.when(flickrService.getByAuhtorId(Mockito.anyString())).thenReturn(java.util.Optional.of(feedEntity));
        ResponseEntity<FeedEntity> actual = flickrController.getFeedByAuthorId(Mockito.anyString());
        Assert.assertEquals(200, actual.getStatusCodeValue());
    }

    @Test
    public void getFeedByAuthorIdNotFound() {
        Mockito.when(flickrService.getByAuhtorId(Mockito.anyString())).thenReturn(Optional.empty());
        ResponseEntity<FeedEntity> actual = flickrController.getFeedByAuthorId(Mockito.anyString());
        Assert.assertEquals(404, actual.getStatusCodeValue());
    }

    @Test
    public void getListFeed() {
        List<FeedEntity> feedEntityList = new ArrayList<>();
        feedEntityList.add(new FeedEntity());
        Mockito.when(flickrService.getListFeed(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(feedEntityList);
        ResponseEntity<List<FeedEntity>> actual = flickrController.getListFeed(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Assert.assertEquals(200, actual.getStatusCodeValue());
    }

    @Test
    public void getListFeedNotFound() {
        List<FeedEntity> feedEntityList = new ArrayList<>();
        Mockito.when(flickrService.getListFeed(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(feedEntityList);
        ResponseEntity<List<FeedEntity>> actual = flickrController.getListFeed(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Assert.assertEquals(404, actual.getStatusCodeValue());
    }

    @Test
    public void deleteAllFeed() {
        Mockito.when(flickrService.deleteAllFeed()).thenReturn("Deleted");
        ResponseEntity<List<FeedEntity>> actual = flickrController.deleteAllFeed();
        Assert.assertEquals(200, actual.getStatusCodeValue());
    }
}