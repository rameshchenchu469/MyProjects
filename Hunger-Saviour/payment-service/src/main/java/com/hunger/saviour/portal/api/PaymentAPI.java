package com.hunger.saviour.portal.api;

import com.hunger.saviour.portal.dto.OrderDTO;
import com.hunger.saviour.portal.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentAPI {

    private final PaymentService paymentService;

    public void createPayment(@RequestBody OrderDTO orderDTO){
        log.info("Entered into PaymentAPI with data : "+orderDTO);
        this.paymentService.processPayment(orderDTO);
    }
}
