package com.lucasgmeneses.shopxyz.data.dto.order;

import com.lucasgmeneses.shopxyz.data.dto.product.RProductResponseDto;

import java.math.BigDecimal;
import java.util.UUID;

public record ROrderProductResponseDto(UUID id,
                                       BigDecimal quantity,
                                       String name,
                                       String description,
                                       BigDecimal price) {
}
