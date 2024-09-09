package com.lucasgmeneses.shopxyz.data.dto.order;

import com.lucasgmeneses.shopxyz.data.model.EOrderStatus;

import java.util.Date;
import java.util.Set;

public record ROrderRequestDto(Date orderDate,
                               Set<ROrderProductRequestDto> products,
                               EOrderStatus status) {
}
