package com.vodahory.statistics;

import com.vodahory.statistics.dtos.GoodsDto;
import com.vodahory.statistics.repository.DisappearRepository;
import com.vodahory.statistics.service.DisappearService;
import com.vodahory.statistics.service.DisappearServiceImpl;
import com.vodahory.statistics.service.GoodsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class DisappearServiceImplTest {

    @Mock
    private DisappearRepository disappearRepository;

    @Mock
    private GoodsServiceImpl goodsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private DisappearService disappearService = new DisappearServiceImpl(
            disappearRepository,
            goodsService
    );


    @Test
    public void testGetSelectedSoldGoods() {
        Long mockFeedId = 1L;
        List<String> mockSelectedGoodsEan = Arrays.asList("ean1", "ean2");

        GoodsDto mockDto1 = new GoodsDto();
        mockDto1.setEan("ean1");
        mockDto1.setTitle("Product1");
        GoodsDto mockDto2 = new GoodsDto();
        mockDto2.setEan("ean2");
        mockDto1.setTitle("Product2");

        List<GoodsDto> mockAllGoods = Arrays.asList(mockDto1, mockDto2);

        when(disappearRepository.findSoldedProductsEans(mockFeedId)).thenReturn(mockSelectedGoodsEan);
        when(goodsService.getAllGoods()).thenReturn(mockAllGoods);

        List<GoodsDto> result = disappearService.getSelectedSoldGoods(mockFeedId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("ean1", result.get(0).getEan());
        assertEquals("ean2", result.get(1).getEan());
    }


}
