package com.example.rav.payment.data;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class PaymentRequest {

    @NotBlank(message = "routeId is mandatory")
    @Positive(message = "routeId can't be negative")
    private String routeId;

    @NotNull(message = "time is mandatory")
    @FutureOrPresent(message = "enter the correct time")
    private LocalDateTime time;

}
