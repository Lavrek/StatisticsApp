package com.vodahory.statistics;

import com.vodahory.statistics.dtos.GoodsDto;
import com.vodahory.statistics.mapper.GoodsDtoToGoods;
import com.vodahory.statistics.model.Availability;
import com.vodahory.statistics.model.Description;
import com.vodahory.statistics.model.Price;
import com.vodahory.statistics.model.Product;
import com.vodahory.statistics.repository.AvailabilityRepository;
import com.vodahory.statistics.repository.DescriptionRepository;
import com.vodahory.statistics.repository.PriceRepository;
import com.vodahory.statistics.repository.ProductRepository;
import com.vodahory.statistics.service.GoodsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

        GoodsDto goodsDto1 = new GoodsDto();
        goodsDto1.setProduct_id(01);
        goodsDto1.setEan("ean1");
        goodsDto1.setProduct_id(01);
        goodsDto1.setTitle("Product1");
        goodsDto1.setPart_number("001");
        goodsDto1.setAvailability_external(1);
        goodsDto1.setAvailability_manufacturer(1);
        goodsDto1.setAvailability_internal(1);
        goodsDto1.setPrice_value("2100");
        goodsDto1.setCurrency("CZK");


        when(productRepository.findAll()).thenReturn(mockProductList);
        when(priceRepository.findAll()).thenReturn(mockPricesList);
        when(availabilityRepository.findAll()).thenReturn(mockAvailabilityList);
        when(goodsDtoToGoods.goodsToGoodsDto(any())).thenReturn(goodsDto1);

        List<GoodsDto> result = goodsService.getAllGoods();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ean1", result.get(0).getEan());
        assertEquals(01, result.get(0).getProduct_id());
        assertEquals("Product1", result.get(0).getTitle());
        assertEquals("001", result.get(0).getPart_number());
        assertEquals("2100", result.get(0).getPrice_value());
        assertEquals(1, result.get(0).getAvailability_manufacturer());
        assertEquals(1, result.get(0).getAvailability_external());
        assertEquals(1, result.get(0).getAvailability_internal());

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
