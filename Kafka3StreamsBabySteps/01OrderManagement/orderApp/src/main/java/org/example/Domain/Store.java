package org.example.Domain;

public record Store(
        String locationId,
        Address address,
        String contactNum
) {
}
