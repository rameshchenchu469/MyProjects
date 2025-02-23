package com.hunger.saviour.portal.scheduler;


import com.hunger.saviour.portal.entities.OrderEntity;
import com.hunger.saviour.portal.entities.OrderStatus;
import com.hunger.saviour.portal.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderStatusSchedulerProcessing {

    private final OrderRepository orderRepository;

    @Scheduled(cron = "0 */2 * * * *")
    public void updateOrderStatus(){
        List<OrderEntity> pendingOrders = this.orderRepository.findByOrderStatus(OrderStatus.ORDER_PENDING);
        pendingOrders.forEach(order -> {
            order.setOrderStatus(OrderStatus.ORDER_APPROVED);
            log.info("Saving data to database");
            this.orderRepository.save(order);
        });
    }
}
