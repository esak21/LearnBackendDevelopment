package org.example.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.example.Domain.Order;
import org.example.Domain.OrderLineItem;
import org.example.Domain.OrderType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Thread.sleep;

@Slf4j
public class OrdersMockDataProducer {

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        publishOrders(objectMapper, buildOrders());
    }

    public static List<Order> buildOrders() {
        var orderItems = List.of(
                new OrderLineItem("Banana", 2, new BigDecimal("2.00")),
                new OrderLineItem("IPhoneCharger", 2, new BigDecimal("19.99")),
                new OrderLineItem("Iphone Case cover", 20, new BigDecimal("200.00"))
                );

        var orderItemsRestaurant = List.of(
                new OrderLineItem("Pizza", 2, new BigDecimal("12.00")),
                new OrderLineItem("Coffee", 1, new BigDecimal("3.00"))
        );


        var order1 = new Order(12345, "store_9899", new BigDecimal("23.25"),
                "GENERAL", orderItems, LocalDateTime.now());

        var order2 = new Order(54321, "store_1234",
                new BigDecimal("15.00"),
                "RESTAURANT",
                orderItemsRestaurant,
                LocalDateTime.now()

        );

        var order3 = new Order(12345, "store_4567",
                new BigDecimal("27.00"),
                "GENERAL",
                orderItems,
                LocalDateTime.now()

        );

        var order4 = new Order(12345, "store_4567",
                new BigDecimal("27.00"),
                "RESTAURANT",
                orderItems,
                LocalDateTime.now()
                //LocalDateTime.now(ZoneId.of("UTC"))
        );

        return List.of(
                order1,
                order2,
                order3,
                order4
        );

    }

    private static void publishBulkOrders(ObjectMapper objectMapper) {
        int count = 0;
        while(count < 100) {
            var orders = buildOrders();
            publishOrders( objectMapper, orders);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count ++;

        }

    }


    public static void publishOrders(ObjectMapper objectMapper, List<Order> orders ) {
        orders.forEach(
                order -> {
                    try {
                        var ordersJson = objectMapper.writeValueAsString(order);
//                         Publish the Message
                        var record  = ProducerUtil.publishMessage("orders", order.orderId()+"", ordersJson);
                        log.info("Published the Message : {}", record.offset());

                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }catch (Exception e) {
                        log.error("Exception : {} ", e.getMessage(), e);
                        throw new RuntimeException(e);
                    }

                }
        );

    }
}
