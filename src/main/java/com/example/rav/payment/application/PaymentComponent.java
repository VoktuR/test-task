package com.example.rav.payment.application;

import com.example.rav.payment.data.PaymentRequest;
import com.example.rav.payment.data.PaymentResponse;
import com.example.rav.payment.entity.PaymentEntity;
import com.example.rav.payment.entity.PaymentState;
import com.example.rav.payment.entity.PaymentsConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentComponent {

    private final PaymentService paymentService;

    @Autowired
    public PaymentComponent(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentResponse createPayment(PaymentRequest request) {
        PaymentEntity paymentEntity = PaymentsConverter.toEntity(request);
        log.debug("Creating a new payment");
        paymentEntity = paymentService.save(paymentEntity);
        return new PaymentResponse()
                .setId(paymentEntity.getId());
    }

    public PaymentState getPaymentState(String id) {
        PaymentEntity paymentEntity = paymentService.findById(id);
        log.debug("Sending back the entity from db");
        if (paymentEntity != null) {
            return paymentEntity.getState();
        } else {
            return null;
        }
    }
}
