package com.vodahory.statistics.controller;

import com.vodahory.statistics.dtos.Feed_viewDto;
import com.vodahory.statistics.dtos.GoodsDto;
import com.vodahory.statistics.exceptions.FeedsNotFoundException;
import com.vodahory.statistics.exceptions.GoodsNotFoundException;
import com.vodahory.statistics.model.Description;
import com.vodahory.statistics.service.DisappearService;
import com.vodahory.statistics.service.Feed_viewService;
import com.vodahory.statistics.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    Feed_viewService feed_viewService;
    @Autowired
    DisappearService disappearService;

    @CrossOrigin(methods = {RequestMethod.GET})
    @GetMapping("products/getAll")
    public List<GoodsDto> getAllProducts() {
        try {
            return goodsService.getAllGoods();
        } catch (GoodsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.GONE, "No goods items found.", e);
        }
    }

    @CrossOrigin(methods = {RequestMethod.GET})
    @GetMapping("store")
    public List<Feed_viewDto> getAllFeeds() {
        try {
            return feed_viewService.getAllFeeds();
        } catch (FeedsNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.GONE, "No feed items found.", ex);
        }
    }

    @CrossOrigin(methods = {RequestMethod.GET})
    @GetMapping("/products/getSelectedGoods/{id}")
    public List<GoodsDto> getSelectedGoods(@PathVariable("id") Long id) {
        try {
            return disappearService.getSelectedSoldGoods(id);
        } catch (GoodsNotFoundException exp) {
            throw new ResponseStatusException(HttpStatus.GONE, "No goods items found.", exp);
        }
    }

    @CrossOrigin(methods = {RequestMethod.GET})
    @GetMapping("/products/getDescription/{ean}")
    public List<Description> getDescription(@PathVariable("ean") String ean) {
        return goodsService.getDescription(ean);
    }

}
