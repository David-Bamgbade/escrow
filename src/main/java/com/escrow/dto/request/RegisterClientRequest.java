package com.escrow.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterClientRequest {
    private long clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
