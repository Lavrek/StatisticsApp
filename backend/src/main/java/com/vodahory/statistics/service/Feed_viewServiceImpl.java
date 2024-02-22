package com.vodahory.statistics.service;

import com.vodahory.statistics.dtos.Feed_viewDto;
import com.vodahory.statistics.exceptions.FeedsNotFoundException;
import com.vodahory.statistics.mapper.Feed_viewDtoToFeed_view;
import com.vodahory.statistics.model.Disappear;
import com.vodahory.statistics.repository.DisappearRepository;
import com.vodahory.statistics.repository.Feed_viewRepository;
import com.vodahory.statistics.repository.PriceRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Setter
@Transactional
@AllArgsConstructor
public class Feed_viewServiceImpl implements Feed_viewService {

    Feed_viewRepository feed_viewRepository;
    Feed_viewDtoToFeed_view feed_viewDtoToFeed_view;
    DisappearRepository disappearRepository;
    GoodsService goodsService;
    Disappear disappear;
    PriceRepository priceRepository;

    @Override
    public List<Feed_viewDto> getAllFeeds() {

        List<Feed_viewDto> list = feed_viewRepository.findAll().stream().map(feed_viewDtoToFeed_view::feed_viewToFeed_viewDto).collect(Collectors.toList());
        Set<Integer> feedIds = list.stream().map(Feed_viewDto::getId).collect(Collectors.toSet());

        Map<Integer, Integer> revenueMap = feedIds.stream()
                .collect(Collectors.toMap(
                        id -> id,
                        this::totalRevenue
                ));

        list.forEach(feed_view -> feed_view.setTotalRevenue(revenueMap.get(feed_view.getId())));
        if (list.isEmpty()) throw new FeedsNotFoundException();
        return list;
    }


    @Override
    public int totalRevenue(int feed_id) {
        List<String> selectedGoodsEan = disappearRepository.findSoldedProductsEans((long) feed_id);
        List<String> prices = new ArrayList<>();

        for (String ean : selectedGoodsEan) {
            try {
                String selectedGoodsPrice = priceRepository.findSoldedProductByEan(ean);
                prices.add(selectedGoodsPrice);
            } catch (StringIndexOutOfBoundsException ex) {
                String selectedGoodsPrice2 = priceRepository.findSoldedProductByEan(ean);
                prices.add(selectedGoodsPrice2);
            }
        }
        List<Integer> intList = new ArrayList<>();

        for (String price : prices) {
            intList.add(Integer.parseInt(price));
        }
        int sum = 0;
        for (int num : intList) {
            sum += num;
        }
        return sum;
    }
}