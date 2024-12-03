package com.escrow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long complainId;
    private String email;
    private String complainMessage;
    private LocalDateTime complainTime;
}
