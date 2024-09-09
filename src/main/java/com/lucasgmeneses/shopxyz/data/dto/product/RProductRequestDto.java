package com.lucasgmeneses.shopxyz.data.dto.product;

import java.math.BigDecimal;

public record RProductRequestDto(String name, String description, BigDecimal price) {
}
