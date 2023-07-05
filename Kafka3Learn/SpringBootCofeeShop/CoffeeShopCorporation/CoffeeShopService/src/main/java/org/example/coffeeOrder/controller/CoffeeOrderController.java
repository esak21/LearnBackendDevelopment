package org.example.coffeeOrder.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.coffeeOrder.dto.CoffeeOrderDTO;
import org.example.coffeeOrder.service.CoffeeOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/coffee_orders")
public class CoffeeOrderController {

    private CoffeeOrderService coffeeOrderService;

    public CoffeeOrderController(CoffeeOrderService coffeeOrderService) {
        this.coffeeOrderService = coffeeOrderService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrderDTO newOrder (@RequestBody CoffeeOrderDTO coffeeOrderDTO) {
        log.info("Received the request for an Order : {} ", coffeeOrderDTO);
        var orderDetails = coffeeOrderService.newOrder(coffeeOrderDTO);
        return coffeeOrderDTO;
    }
}
