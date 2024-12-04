package com.escrow.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class ViewClientComplainResponse {
    private Long clientComplainId;
    private String sellerName;
    private String sellerPhoneNumber;
    private String productName;
    private BigDecimal productPrice;
    private String complainMessage;
    private LocalDateTime pastTransactionTime;
    private String transactionPaymentStatus;
    private String clientPhoneNumber;
    private LocalDateTime complainTime;
    private boolean success;
}
