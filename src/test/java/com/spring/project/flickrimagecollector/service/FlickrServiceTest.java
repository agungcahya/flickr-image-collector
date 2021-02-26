package com.spring.project.flickrimagecollector.service;

import com.spring.project.flickrimagecollector.entity.FeedEntity;
import com.spring.project.flickrimagecollector.repository.FeedRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class FlickrServiceTest {

    @InjectMocks
    FlickrService flickrService;

    @Mock
    FeedRepository feedRepository;

    @Test
    public void pullFeeds() {

    }

    @Test
    public void getByAuhtorId() {
        FeedEntity feedEntity = new FeedEntity();
        Mockito.when(feedRepository.findByAuthorId(Mockito.anyString())).thenReturn(java.util.Optional.of(feedEntity));
        Optional<FeedEntity> actual = flickrService.getByAuhtorId(Mockito.anyString());
        Assert.assertNotNull(actual);
    }

    @Test
    public void getByAuhtorIdNotFound() {
        Mockito.when(feedRepository.findByAuthorId(Mockito.anyString())).thenReturn(null);
        Optional<FeedEntity> actual = flickrService.getByAuhtorId(Mockito.anyString());
        Assert.assertNull(actual);
    }

    @Test
    public void getListFeed() {
        List<FeedEntity> feedEntityList = new ArrayList<>();
        Mockito.when(feedRepository.getListFeed(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(feedEntityList);
        List<FeedEntity> actual = flickrService.getListFeed(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Assert.assertNotNull(actual);
    }

}