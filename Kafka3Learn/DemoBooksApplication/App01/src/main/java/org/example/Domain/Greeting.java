package org.example.Domain;

import java.time.LocalDateTime;

public record  Greeting (
        String message,
        LocalDateTime timestamp
) {

}
