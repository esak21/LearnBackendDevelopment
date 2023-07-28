package org.example.Domain;

import java.math.BigDecimal;

public record OrderLineItem(String item,
                            Integer count,
                            BigDecimal amount ) {
}
