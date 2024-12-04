package com.escrow.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginClientRequest {
    public String password;
    private String clientEmail;
}
