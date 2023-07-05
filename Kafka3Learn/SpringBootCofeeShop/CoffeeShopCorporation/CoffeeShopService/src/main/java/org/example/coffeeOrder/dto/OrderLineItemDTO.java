package org.example.coffeeOrder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.coffeeshop.domain.generated.Size;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDTO {
    private String name;
    private Size size;
    private int quantity;
    private BigDecimal cost;
}
