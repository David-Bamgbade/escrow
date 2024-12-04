package com.escrow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class ClientComplain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String complainResolved;
}
