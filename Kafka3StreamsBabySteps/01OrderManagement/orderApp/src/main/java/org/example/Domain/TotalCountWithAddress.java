package org.example.Domain;

import java.math.BigDecimal;

public record TotalCountWithAddress(
        Long count,
        Store store
) {
    public TotalCountWithAddress() {
        this( 0l, new Store("", new Address("", "", "", "",""), ""));
    }


}
