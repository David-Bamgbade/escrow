package com.escrow.dto.request;

import com.escrow.model.BankName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class EscrowPaymentRequest {
    private String sellerPhoneNumber;
    private BigDecimal productPrice;
}
