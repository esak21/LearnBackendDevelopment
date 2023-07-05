package org.example.coffeeOrder.service;

import org.coffeeshop.domain.generated.Address;
import org.coffeeshop.domain.generated.CoffeeOrder;
import org.coffeeshop.domain.generated.OrderLineItem;
import org.coffeeshop.domain.generated.Store;
import org.example.coffeeOrder.dto.CoffeeOrderDTO;
import org.example.coffeeOrder.dto.OrderLineItemDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CoffeeOrderService {

    public CoffeeOrderDTO newOrder(CoffeeOrderDTO coffeeOrderDTO) {

        var coffeeOrderAvro = mapToCoffeeOrder(coffeeOrderDTO);
         coffeeOrderDTO.setId(coffeeOrderAvro.getId().toString());
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
}
