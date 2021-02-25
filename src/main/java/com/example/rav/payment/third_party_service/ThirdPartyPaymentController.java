package com.example.rav.payment.third_party_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("third-party/payments")
public class ThirdPartyPaymentController {

    private final ThirdPartyPaymentService thirdPartyPaymentService;

    @Autowired
    public ThirdPartyPaymentController(ThirdPartyPaymentService thirdPartyPaymentService) {
        this.thirdPartyPaymentService = thirdPartyPaymentService;
    }

    @GetMapping("process")
    public boolean process(@RequestParam String id) {
        log.debug("Received payment for state change");
        return thirdPartyPaymentService.process(id);
    }

}
