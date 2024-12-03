package com.escrow.dto.request;

import com.escrow.model.BankName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SellerPaymentDetailsRequest {
    private String sellerName;
    private BigDecimal productPrice;
    private String sellerPhoneNumber;
    private String sellerEmail;
    private String sellerAccountNumber;
    private BankName sellerBankName;
    private String productName;
}
