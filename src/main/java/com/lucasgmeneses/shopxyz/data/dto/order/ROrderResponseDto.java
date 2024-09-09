package com.lucasgmeneses.shopxyz.data.dto.order;

import com.lucasgmeneses.shopxyz.data.model.EOrderStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record ROrderResponseDto(UUID id,
                                Date orderDate,
                                EOrderStatus status,
                                List<ROrderProductResponseDto> products) {
}
