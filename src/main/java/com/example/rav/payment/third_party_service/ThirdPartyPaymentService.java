package com.example.rav.payment.third_party_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class ThirdPartyPaymentService {

    public boolean process(String id) {
        log.debug("Choosing new state for payment");
        return new Random().nextBoolean();
    }

}
