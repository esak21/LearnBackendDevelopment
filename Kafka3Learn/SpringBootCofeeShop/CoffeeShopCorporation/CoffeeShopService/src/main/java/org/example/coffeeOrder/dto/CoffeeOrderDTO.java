package org.example.coffeeOrder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.coffeeshop.domain.generated.PickUp;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeOrderDTO {
    private String id;
    private String name;

    private String nickName;

    private StoreDTO store;

    private List<OrderLineItemDTO> orderLineItems;

    private PickUp pickup;

    private String status;

    private LocalDateTime orderedTime;


}
