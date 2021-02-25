package com.example.rav.payment.application;

import com.example.rav.payment.data.PaymentRequest;
import com.example.rav.payment.data.PaymentResponse;
import com.example.rav.payment.entity.PaymentState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(value = "payments", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

    private final PaymentComponent paymentComponent;

    @Autowired
    public PaymentController(PaymentComponent paymentComponent) {
        this.paymentComponent = paymentComponent;
    }

    @PostMapping("create")
    public PaymentResponse createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        log.info("New payment was received");
        return paymentComponent.createPayment(paymentRequest);
    }

    @GetMapping("get-state")
    public ResponseEntity getPaymentState(@RequestParam String id) {
        log.info("Status request for payment " + id + " was received");
        PaymentState state = paymentComponent.getPaymentState(id);
        if (state != null) {
            log.debug("This payment was found");
            return new ResponseEntity<>(state, HttpStatus.OK);
        } else {
            log.debug("There is no such payment");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
