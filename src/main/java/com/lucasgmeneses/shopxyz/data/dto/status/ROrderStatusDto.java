package com.lucasgmeneses.shopxyz.data.dto.status;

import com.lucasgmeneses.shopxyz.data.model.EOrderStatus;

import java.util.UUID;

public record ROrderStatusDto(UUID idOrder, EOrderStatus status) {
}
