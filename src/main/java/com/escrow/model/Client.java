package com.escrow.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.util.List;

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
    private Transaction transaction;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Complain complain;
}
