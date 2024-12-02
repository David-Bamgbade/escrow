package com.escrow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private String sellerName;
    private String sellerEmailAddress;
    private String sellerPhoneNumber;
    private String productPrice;
    private String productName;
    private String sellerAccountNumber;
    @Enumerated(EnumType.STRING)
    @OneToOne(cascade = CascadeType.PERSIST)
    private Complain complain;
    private BankName bankName;
}
