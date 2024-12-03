package com.escrow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
}
