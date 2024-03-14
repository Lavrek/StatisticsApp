package com.statistics.service;

import com.statistics.exceptions.GoodsNotFoundException;
import com.statistics.repository.DisappearRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import statistics.Item;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DisappearServiceImpl implements DisappearService {

    DisappearRepository disappearRepository;
    GoodsServiceImpl goodsService;

    @Override
    public List<Item> getSelectedSoldGoods(Long feed_id) {
        List<String> selectedGoodsEan = disappearRepository.findSoldedProductsEans(feed_id);

        List<Item> allGoods = goodsService.getAllGoods();
        List<Item> selectedSoldGoods = new ArrayList<>();
        for (String ean :
                selectedGoodsEan) {
            for (Item item : allGoods) {
                if (item.getEan().equals(ean)) {
                    selectedSoldGoods.add(item);
                }
            }
        }
        if (selectedSoldGoods.isEmpty()) throw new GoodsNotFoundException();
        return selectedSoldGoods;
    }
}