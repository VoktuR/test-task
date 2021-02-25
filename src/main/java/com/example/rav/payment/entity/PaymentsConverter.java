package com.example.rav.payment.entity;

import com.example.rav.payment.data.PaymentRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentsConverter {

    public static PaymentEntity toEntity(PaymentRequest request) {
        log.debug("Converting request to entity");
        return new PaymentEntity()
                .setState(PaymentState.NEW)
                .setRouteId(request.getRouteId())
                .setTime(request.getTime());
    }

}
