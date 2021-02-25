package com.example.rav.payment.application;

import com.example.rav.payment.entity.PaymentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public PaymentService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public PaymentEntity save(PaymentEntity paymentEntity) {
        log.debug("save method was called at @Component");
        return mongoTemplate.save(paymentEntity);
    }

    public PaymentEntity findById(String id) {
        log.debug("findById method was called at @Component");
        return mongoTemplate.findById(id, PaymentEntity.class);
    }
}
