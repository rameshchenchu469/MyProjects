package com.hunger.saviour.portal.apis;

import com.hunger.saviour.portal.dtos.OrderDTO;
import com.hunger.saviour.portal.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class OrderAPI {

    @Autowired
    private OrderService orderService;

    public List<OrderDTO> getOrdersByUsername(String username){
        return null;
    }

}
