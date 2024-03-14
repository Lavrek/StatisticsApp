package com.statistics.service;

import com.statistics.exceptions.FeedsNotFoundException;
import com.statistics.mapper.Feed_viewDtoToFeed_view;
import com.statistics.model.Disappear;
import com.statistics.model.Feed_view;
import com.statistics.repository.DisappearRepository;
import com.statistics.repository.Feed_viewRepository;
import com.statistics.repository.PriceRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import statistics.Feed;

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
    Feed_viewDtoToFeed_view mapper;
    DisappearRepository disappearRepository;
    GoodsService goodsService;
    Disappear disappear;
    PriceRepository priceRepository;

    @Override
    public List<Feed> getAllFeeds() {

            List<Feed> list = feed_viewRepository.findAll().stream()
                    .map(mapper::feed_viewToFeed_viewDto)
                    .collect(Collectors.toList());
            Set<Integer> feedIds = list.stream().map(Feed::getId).collect(Collectors.toSet());

            Map<Integer, Integer> revenueMap = feedIds.stream()
                    .collect(Collectors.toMap(
                            id -> id,
                            this::totalRevenue
                    ));

            list.forEach(e -> e.setTotalRevenue(revenueMap.get(e.getId())));
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