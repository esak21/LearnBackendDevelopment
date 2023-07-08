package org.example.coffeeOrder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.coffeeshop.domain.generated.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoffeeOrderUpdateDTO {

    private OrderStatus orderstatus;

}
