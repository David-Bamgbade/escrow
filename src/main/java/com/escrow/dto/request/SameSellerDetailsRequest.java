package com.escrow.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SameSellerDetailsRequest {
    private String productName;
    private BigDecimal productPrice;
    private String sellerPhoneNumber;
    private String clientPhoneNumber;
}
