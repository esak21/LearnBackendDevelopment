package org.example.coffeeOrder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

    private String addressLine1;
    private String city;
    private String state;
    private String country;
    private String zip;

}
