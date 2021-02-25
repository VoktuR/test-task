package com.example.rav.payment.sheduler;

import com.example.rav.payment.entity.PaymentEntity;
import com.example.rav.payment.entity.PaymentState;
import com.example.rav.payment.entity.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class PaymentSchedule {

    private final PaymentRepository paymentRepository;

    private RestTemplate restTemplate;

    @Autowired
    public PaymentSchedule(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(fixedDelay = 60 * 1000)
    public void processPayment() {
        log.info("Start scheduling: " + LocalDateTime.now());

        List<PaymentEntity> payments = paymentRepository.findByState(PaymentState.NEW);

        payments.forEach(paymentEntity ->  {
                    log.info("Processing started: " + paymentEntity.getId());
                    paymentEntity.setState(PaymentState.PROCESSING);
                    paymentRepository.save(paymentEntity);

                    ResponseEntity<Boolean> response = process(paymentEntity.getId());
                    if (response.getStatusCode().is2xxSuccessful()) {
                        Boolean result = response.getBody();
                            if (result) {
                                paymentEntity.setState(PaymentState.SUCCESS);
                            } else {
                                paymentEntity.setState(PaymentState.FAILED);
                            }
                    }
                    paymentRepository.save(paymentEntity);
        });
        log.info("All proper payments were processed: " + LocalDateTime.now());

    }

    private ResponseEntity<Boolean> process(String id) {
        String url = "http://localhost:8080/service/third-party/payments/process?id=" + id;
        log.debug("Sending request to third party service");
        return restTemplate.getForEntity(url, Boolean.class);
    }

}
