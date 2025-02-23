package com.hunger.saviour.portal.service.Impl;

import com.hunger.saviour.portal.dto.OrderDTO;
import com.hunger.saviour.portal.entity.PaymentEntity;
import com.hunger.saviour.portal.entity.PaymentStatus;
import com.hunger.saviour.portal.kafka.PaymentServiceKafkaPublisher;
import com.hunger.saviour.portal.repository.PaymentRepository;
import com.hunger.saviour.portal.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

        private final PaymentRepository paymentRepository;
        private final PaymentServiceKafkaPublisher paymentServiceKafkaPublisher;

        @Override
        public void processPayment(OrderDTO orderDTO) {
            PaymentEntity paymentEntity = PaymentEntity.builder()
                    .paymentStatus(PaymentStatus.PAYMENT_SUCCESS)
                    .transactionId(orderDTO.getTransactionId())
                    .username(orderDTO.getUsername())
                    .txnDateAndTime(LocalDateTime.now())
                    .build();
            this.paymentRepository.save(paymentEntity);
            this.paymentServiceKafkaPublisher.publishOrderDetailsToOrdersTopic(orderDTO);
        }
    }

