package com.escrow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private BankName bankName;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private SellerDetails sellerDetails;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Complain complain;
    @OneToOne
    private EscrowAccount escrowAccount;
    private boolean loggedIn;
}
