package com.lucasgmeneses.shopxyz.service;

import com.lucasgmeneses.shopxyz.data.dto.status.ROrderStatusDto;
import com.lucasgmeneses.shopxyz.data.model.OrderModel;
import com.lucasgmeneses.shopxyz.repository.IOrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusConsumerService {
    @Autowired
    private IOrderRepository orderRepository;

    @RabbitListener(queues = "orders.changed-status")
    public void handleOrderStatusUpdate(ROrderStatusDto event) {

        OrderModel existingOrder = orderRepository.findById(event.idOrder()).orElse(null);
        if(existingOrder != null){
            existingOrder.setStatus(event.status());
            orderRepository.save(existingOrder);
        }
    }
}
