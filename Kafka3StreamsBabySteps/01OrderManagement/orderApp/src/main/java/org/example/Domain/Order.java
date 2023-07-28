package org.example.Domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record Order(Integer orderId,
                    String locationId,
                    BigDecimal finalAmount,
                    String orderType,
                    List<OrderLineItem> orderLineItems,
                    LocalDateTime localDateTime ) {
}
