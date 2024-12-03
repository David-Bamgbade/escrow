package com.escrow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class SellerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;
    private String sellerName;
    private String sellerEmailAddress;
    private String sellerPhoneNumber;
    private BigDecimal productPrice;
    private String productName;
    private String sellerAccountNumber;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Complain complainOrFeedBack;
    @Enumerated(EnumType.STRING)
    private BankName sellerBankName;
    private String clientPaymentStatus;
    private String clientPhoneNumber;
    private LocalDateTime createdOn;
    private LocalDateTime clientPaymentDate;
}
