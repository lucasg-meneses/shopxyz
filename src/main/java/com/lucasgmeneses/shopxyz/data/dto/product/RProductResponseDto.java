package com.lucasgmeneses.shopxyz.data.dto.product;

import java.math.BigDecimal;
import java.util.UUID;

public record RProductResponseDto(UUID id, String name, String description, BigDecimal price) {

}
