package com.vodahory.statistics.service;

import com.vodahory.statistics.dtos.GoodsDto;
import com.vodahory.statistics.exceptions.GoodsNotFoundException;
import com.vodahory.statistics.repository.DisappearRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DisappearServiceImpl implements DisappearService {

    DisappearRepository disappearRepository;
    GoodsServiceImpl goodsService;

    @Override
    public List<GoodsDto> getSelectedSoldGoods(Long feed_id) {
        List<String> selectedGoodsEan = disappearRepository.findSoldedProductsEans(feed_id);

        List<GoodsDto> allGoods = goodsService.getAllGoods();
        List<GoodsDto> selectedSoldGoods = new ArrayList<>();
        for (String ean :
                selectedGoodsEan) {
            for (GoodsDto goodsDto : allGoods) {
                if (goodsDto.getEan().equals(ean)) {
                    selectedSoldGoods.add(goodsDto);
                }
            }
        }
        if (selectedSoldGoods.isEmpty()) throw new GoodsNotFoundException();
        return selectedSoldGoods;
    }
}