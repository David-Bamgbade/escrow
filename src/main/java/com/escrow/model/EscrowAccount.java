package com.escrow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class EscrowAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long escrowId;
    private BigDecimal productPrice;
    private String productName;
    private String sellerPhoneNumber;
    private String sellerAccountNumber;
    @Enumerated(EnumType.STRING)
    private BankName sellerBankName;
    private String sellerName;
    private String paymentStatus;
    private String clientPhoneNumber;
    private LocalDateTime paymentDate;
    @Enumerated(EnumType.STRING)
    private BankName escrowBank;
    private String escrowAccountNumber;
}
