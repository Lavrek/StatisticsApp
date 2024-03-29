package com.statistics;

import com.statistics.mapper.Feed_viewDtoToFeed_view;
import com.statistics.model.Disappear;
import com.statistics.model.Feed_view;
import com.statistics.repository.DisappearRepository;
import com.statistics.repository.Feed_viewRepository;
import com.statistics.repository.PriceRepository;
import com.statistics.service.Feed_viewService;
import com.statistics.service.Feed_viewServiceImpl;
import com.statistics.service.GoodsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import statistics.Feed;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Feed_viewServiceImplTest {

    @Mock
    private Feed_viewRepository feed_viewRepository;

    @Mock
    private Feed_viewDtoToFeed_view feed_viewDtoToFeed_view;

    @Mock
    private DisappearRepository disappearRepository;

    @Mock
    private GoodsService goodsService;

    @Mock
    private Disappear disappear;

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private Feed_viewService feed_viewService = new Feed_viewServiceImpl(
            feed_viewRepository,
            feed_viewDtoToFeed_view,
            disappearRepository,
            goodsService,
            disappear,
            priceRepository
    );

    private Feed_viewService spyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        spyService = spy(feed_viewService);
    }

    @Test
    public void testGetAllFeeds() {

        Feed_view feedView1 = new Feed_view();
        feedView1.setId(1);
        feedView1.setFeed_name("FeedName");
        feedView1.setCount(123);
        feedView1.setSold(5);
        Feed_view feedView2 = new Feed_view();
        feedView2.setId(2);

        List<Feed_view> mockFeedViewList = Arrays.asList(feedView1, feedView2);

        Feed feedViewDto1 = new Feed();
        feedViewDto1.setId(1);
        feedViewDto1.setFeedName("FeedName");
        feedViewDto1.setCount(123);
        feedViewDto1.setSold(5);
        Feed feedViewDto2 = new Feed();
        feedViewDto2.setId(2);

        when(feed_viewRepository.findAll()).thenReturn(mockFeedViewList);
        when(feed_viewDtoToFeed_view.feed_viewToFeed_viewDto(feedView1)).thenReturn(feedViewDto1);
        when(feed_viewDtoToFeed_view.feed_viewToFeed_viewDto(feedView2)).thenReturn(feedViewDto2);
        doReturn(100).when(spyService).totalRevenue(1);
        doReturn(100).when(spyService).totalRevenue(2);

        List<Feed> result = feed_viewService.getAllFeeds();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("FeedName", result.get(0).getFeedName());
        assertEquals(123, result.get(0).getCount());
        assertEquals(5, result.get(0).getSold());
    }


    @Test
    public void testTotalRevenue() {
        MockitoAnnotations.initMocks(this);

        int feed_id = 3;

        List<String> disappearList = Arrays.asList("11", "12");
        String pricesbyEan1 = "2090";
        String pricesbyEan2 = "2000";


        when(disappearRepository.findSoldedProductsEans((long) feed_id)).thenReturn(disappearList);
        when(priceRepository.findSoldedProductByEan("11")).thenReturn(pricesbyEan1);
        when(priceRepository.findSoldedProductByEan("12")).thenReturn(pricesbyEan2);

        int totalRevenue = feed_viewService.totalRevenue(feed_id);

        assertEquals(4090, totalRevenue);
    }
}

