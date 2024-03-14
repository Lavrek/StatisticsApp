package com.statistics;

import com.statistics.mapper.GoodsDtoToGoods;
import com.statistics.model.Description;
import com.statistics.model.Price;
import com.statistics.repository.AvailabilityRepository;
import com.statistics.repository.DescriptionRepository;
import com.statistics.repository.PriceRepository;
import com.statistics.repository.ProductRepository;
import com.statistics.service.GoodsServiceImpl;
import com.statistics.model.Availability;
import com.statistics.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import statistics.Item;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GoodsServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private PriceRepository priceRepository;
    @Mock
    AvailabilityRepository availabilityRepository;
    @Mock
    DescriptionRepository descriptionRepository;
    @Mock
    GoodsDtoToGoods goodsDtoToGoods;
    @InjectMocks
    private GoodsServiceImpl goodsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllGoods() {

        Product product1 = new Product();
        product1.setEan("ean1");
        product1.setId(1);
        product1.setProduct_id(01);
        product1.setTitle("Product1");
        product1.setName("Product1Exp1");
        product1.setPart_number("001");

        List<Product> mockProductList = Arrays.asList(product1);

        Price price1 = new Price();
        price1.setEan("ean1");
        price1.setPrice_value("2100");
        price1.setCurrency("CZK");

        List<Price> mockPricesList = Arrays.asList(price1);

        Availability availability1 = new Availability();
        availability1.setEan("ean1");
        availability1.setManufacturer(1);
        availability1.setInternal(1);
        availability1.setExternal(1);

        List<Availability> mockAvailabilityList = Arrays.asList(availability1);

        Item goodsDto1 = new Item();
        goodsDto1.setProductId(01);
        goodsDto1.setEan("ean1");
        goodsDto1.setTitle("Product1");
        goodsDto1.setPartNumber("001");
        goodsDto1.setAvailabilityExternal(1);
        goodsDto1.setAvailabilityManufacturer(1);
        goodsDto1.setAvailabilityInternal(1);
        goodsDto1.setPriceValue("2100");
        goodsDto1.setCurrency("CZK");


        when(productRepository.findAll()).thenReturn(mockProductList);
        when(priceRepository.findAll()).thenReturn(mockPricesList);
        when(availabilityRepository.findAll()).thenReturn(mockAvailabilityList);
        when(goodsDtoToGoods.goodsToGoodsDto(any())).thenReturn(goodsDto1);

        List<Item> result = goodsService.getAllGoods();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ean1", result.get(0).getEan());
        assertEquals(01, result.get(0).getProductId());
        assertEquals("Product1", result.get(0).getTitle());
        assertEquals("001", result.get(0).getPartNumber());
        assertEquals("2100", result.get(0).getPriceValue());
        assertEquals(1, result.get(0).getAvailabilityManufacturer());
        assertEquals(1, result.get(0).getAvailabilityExternal());
        assertEquals(1, result.get(0).getAvailabilityInternal());

        verify(productRepository, times(1)).findAll();
        verify(priceRepository, times(1)).findAll();
        verify(availabilityRepository, times(1)).findAll();
    }

    @Test
    public void testGetDescription() {

        Description description1 = new Description();
        description1.setEan("ean1");
        description1.setShort_description("product1");
        description1.setLarge_description("moreAboutProduct1");

        List<Description> mockDescriptionList = Arrays.asList(description1);

        when(descriptionRepository.descriptiontByEan("ean1")).thenReturn(mockDescriptionList);

        List<Description> result = goodsService.getDescription("ean1");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("product1", result.get(0).getShort_description());
        assertEquals("moreAboutProduct1", result.get(0).getLarge_description());

    }
}
