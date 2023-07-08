package org.example.coffeeOrder.service;

import org.coffeeshop.domain.generated.*;
import org.example.coffeeOrder.dto.CoffeeOrderDTO;
import org.example.coffeeOrder.dto.CoffeeOrderUpdateDTO;
import org.example.coffeeOrder.dto.OrderLineItemDTO;
import org.example.coffeeOrder.producer.CoffeeOrderProducer;
import org.example.coffeeOrder.producer.CoffeeOrderUpdateProducer;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CoffeeOrderService {


    private CoffeeOrderProducer coffeeOrderProducer;

    private CoffeeOrderUpdateProducer coffeeOrderUpdateProducer;

    public CoffeeOrderService(CoffeeOrderProducer coffeeOrderProducer, CoffeeOrderUpdateProducer coffeeOrderUpdateProducer) {
        this.coffeeOrderProducer = coffeeOrderProducer;
        this.coffeeOrderUpdateProducer = coffeeOrderUpdateProducer;
    }

    public CoffeeOrderDTO newOrder(CoffeeOrderDTO coffeeOrderDTO) {

        var coffeeOrderAvro = mapToCoffeeOrder(coffeeOrderDTO);
         coffeeOrderDTO.setId(coffeeOrderAvro.getId().toString());
         coffeeOrderProducer.sendCoffeeOrderMessage(coffeeOrderAvro);
         return coffeeOrderDTO;
    }

    private CoffeeOrder mapToCoffeeOrder(CoffeeOrderDTO coffeeOrderDTO) {
        var store = getStoreDetails(coffeeOrderDTO);
        var orderLIneItems = getOrderLineItems(coffeeOrderDTO.getOrderLineItems());
        return CoffeeOrder.newBuilder()
                .setId(UUID.randomUUID())
                .setName(coffeeOrderDTO.getName())
                .setStore(store)
                .setOrderLineItems(orderLIneItems)
                .setOrderedTime(Instant.now())
                .setStatus(coffeeOrderDTO.getStatus())
                .build();
    }

    private List<OrderLineItem> getOrderLineItems(List<OrderLineItemDTO> orderLineItemDTOList) {
       return  orderLineItemDTOList
                .stream()
                .map(
                        rec -> new OrderLineItem(rec.getName(), rec.getSize(), rec.getQuantity(), rec.getCost())
                ).collect(Collectors.toList());
    }

    private Store getStoreDetails(CoffeeOrderDTO coffeeOrderDTO) {
        var store = coffeeOrderDTO.getStore();
        return Store.newBuilder()
                .setId(store.getStoreid())
                .setAddress(
                        new Address(
                            store.getAddress().getAddressLine1(),
                            store.getAddress().getCity(),
                            store.getAddress().getState(),
                            store.getAddress().getCountry(),
                            store.getAddress().getZip()
                        )
                ).build();
    }

    public CoffeeOrderUpdateDTO coffeeOrderStatusUpdate(String orderid, CoffeeOrderUpdateDTO coffeeOrderUpdateDTO) {
        var coffeeUpdateEvent = mapToCoffeeOrderUpdate(orderid, coffeeOrderUpdateDTO);
        System.out.println(coffeeUpdateEvent);
        coffeeOrderUpdateProducer.sendCoffeeOrderMessage(coffeeUpdateEvent);
        return coffeeOrderUpdateDTO;
    }

    private CoffeeUpdateEvent mapToCoffeeOrderUpdate(String orderid, CoffeeOrderUpdateDTO coffeeOrderUpdateDTO) {

        return  CoffeeUpdateEvent.newBuilder()
                .setId(UUID.fromString(orderid))
                .setStatus(coffeeOrderUpdateDTO.getOrderstatus())
                .build();
    }
}
