package com.example.rav.payment.entity.repository;

import com.example.rav.payment.entity.PaymentEntity;
import com.example.rav.payment.entity.PaymentState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {

    List<PaymentEntity> findByState(PaymentState state);

}
