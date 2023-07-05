package org.coffeeshop.util;

import org.coffeeshop.domain.generated.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CofeeOrderUtil {

    public static int randomId(){
        Random random = new Random();
        return random.nextInt(1000);
    }
    private static Address buildAddress() {

        return Address.newBuilder()
                .setAddressLine1("1234 Address Line 1")
                .setCity("Chicago")
                .setStateProvince("IL")
                .setZip("12345")
                .setCountry("INDIA")
                .build();

    }

    private static Store generateStore(){

        return  Store.newBuilder()
                .setId(randomId())
                .setAddress(buildAddress())
                .build();
    }

    private static List<OrderLineItem> generateOrderLineItems() {

        var orderLineItem = OrderLineItem.newBuilder()
                .setName("Caffe Latte")
                .setQuantity(1)
                .setSize(Size.MEDIUM)
                .setCost(BigDecimal.valueOf(3.99))
                .build();

        return List.of(orderLineItem);

    }



    public static CoffeeOrder buildnewCoffeeOrder() {

        var ApporderId = OrderId.newBuilder().setId(randomId()).build();
        return CoffeeOrder.newBuilder().
                setId(ApporderId).
                setName("Esakki Thangappapillai").
                setNickName("Esak")
                .setStore(generateStore())
                .setOrderLineItems(generateOrderLineItems())
                .setOrderedData(LocalDate.now())
                .setOrderedTime(Instant.now())
                .setStatus("NEW")
                .build();
    }
}
