package com.lucasgmeneses.shopxyz.data.dto.order;

import java.math.BigDecimal;
import java.util.UUID;

public record ROrderProductRequestDto(UUID idProduct,
                                      BigDecimal  quantity) {
}
