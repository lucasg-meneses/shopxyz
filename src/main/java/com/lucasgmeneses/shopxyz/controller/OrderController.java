package com.lucasgmeneses.shopxyz.controller;

import com.lucasgmeneses.shopxyz.data.dto.order.*;
import com.lucasgmeneses.shopxyz.data.dto.status.ROrderStatusDto;
import com.lucasgmeneses.shopxyz.data.model.EOrderStatus;
import com.lucasgmeneses.shopxyz.data.model.OrderModel;
import com.lucasgmeneses.shopxyz.data.model.OrderProductModel;
import com.lucasgmeneses.shopxyz.data.model.ProductModel;
import com.lucasgmeneses.shopxyz.repository.IOrderRepository;
import com.lucasgmeneses.shopxyz.repository.IProductRepository;
import com.lucasgmeneses.shopxyz.service.OrderStatusPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;


@RestController
@RequestMapping("/order")
public class OrderController implements ICrudController<ROrderRequestDto, ROrderUpdatedRequestDto> {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private OrderStatusPublisherService orderStatusPublisherService;

    @Override
    @PostMapping
    public ResponseEntity create(ROrderRequestDto request) {
        try {
            var orderModel = new OrderModel();
            orderModel.setOrderDate(request.orderDate()!= null ? request.orderDate() : new Date());
            orderModel.setStatus(request.status() != null ? request.status(): EOrderStatus.PENDING);
            for (ROrderProductRequestDto it : request.products()){
                ProductModel productModel = productRepository.findById(it.idProduct()).orElse(null);
                if(productModel != null){
                    OrderProductModel orderProductModel = new OrderProductModel();
                    orderProductModel.setProduct(productModel);
                    orderProductModel.setQuantity(it.quantity());
                    orderModel.addProduct(orderProductModel);
                    orderProductModel.setOrder(orderModel);
                }
            }
            orderRepository.save(orderModel);
            return ResponseEntity.ok(createOrderResponse(orderModel));
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity update(UUID id, ROrderUpdatedRequestDto request) {
        try {
            OrderModel orderModel = orderRepository.findById(id).orElse(null);
            if(orderModel == null) return ResponseEntity.notFound().build();

            orderModel.setOrderDate(request.orderDate()!= null ? request.orderDate() : orderModel.getOrderDate()  );
            orderModel.setStatus(request.status() != null ? request.status(): orderModel.getStatus());

            for(ROrderProductUpdatedRequestDto itRequest : request.products()){
                for (OrderProductModel itModel: orderModel.getOrderProducts()){
                    if(itModel.getId().equals(itRequest.id())){
                        if(itRequest.idProduct() != null
                                && !itModel.getProduct().getId().equals(itRequest.idProduct())){

                            ProductModel productModel = productRepository.findById(itRequest.idProduct()).orElse(null);
                            if(productModel == null) return ResponseEntity.badRequest().build();
                            itModel.setProduct(productModel);
                        }
                        itModel.setQuantity(itRequest.quantity() != null ? itRequest.quantity(): itModel.getQuantity());
                    }
                }
            }
            orderRepository.save(orderModel);
            return ResponseEntity.ok(createOrderResponse(orderModel));
        }catch (Exception ex){
            return  ResponseEntity.badRequest().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(UUID id) {
        OrderModel orderModel = orderRepository.findById(id).orElse(null);
        if(orderModel == null) return ResponseEntity.notFound().build();

        orderRepository.delete(orderModel);
        return ResponseEntity.ok(createOrderResponse(orderModel));
    }

    @Override
    @GetMapping("/id")
    public ResponseEntity get(UUID id) {
        OrderModel orderModel = orderRepository.findById(id).orElse(null);
        if(orderModel == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(createOrderResponse(orderModel));
    }

    @Override
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(orderRepository.findAll().stream()
                .map(it -> createOrderResponse(it)).toList());
    }
    @PostMapping("/changestatus")
    public ResponseEntity changeStatus(@RequestBody ROrderStatusDto request){
        OrderModel orderModel = orderRepository.findById(request.idOrder()).orElse(null);
        if(orderModel == null) return ResponseEntity.notFound().build();

        orderStatusPublisherService.updateOrderStatus(request);

        return ResponseEntity.ok().build();
    }


    private ROrderResponseDto createOrderResponse(OrderModel order){
        return new ROrderResponseDto(
                order.getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getOrderProducts().stream()
                        .map(it-> new ROrderProductResponseDto(
                                it.getId(),
                                it.getQuantity(),
                                it.getProduct().getName(),
                                it.getProduct().getDescription(),
                                it.getProduct().getPrice()))
                        .toList());
    }


}
