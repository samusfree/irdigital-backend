package com.samus.irdigital.backend.api.client.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ClientKPI {
    private BigDecimal averageAge;
    private BigDecimal standardDeviation;
}
