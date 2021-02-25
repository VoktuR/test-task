package com.example.rav.payment.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class PaymentEntity {

    @Id
    private String id;

    private PaymentState state;
    private String routeId;
    private LocalDateTime time;

}
