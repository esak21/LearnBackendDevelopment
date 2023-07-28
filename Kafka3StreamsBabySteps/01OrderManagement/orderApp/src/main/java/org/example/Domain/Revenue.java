package org.example.Domain;

import java.math.BigDecimal;

public record Revenue(String locationId ,
                      BigDecimal finalAmount ) {
}
