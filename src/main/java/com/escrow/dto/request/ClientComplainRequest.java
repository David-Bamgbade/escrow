package com.escrow.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientComplainRequest {
    private String complainMessage;
    private String email;

}
