package com.lucasgmeneses.shopxyz.service;

import com.lucasgmeneses.shopxyz.data.dto.status.ROrderStatusDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusPublisherService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void updateOrderStatus(ROrderStatusDto orderStatusDto) {
        rabbitTemplate.convertAndSend("orders.changed-status", orderStatusDto);
    }
}
