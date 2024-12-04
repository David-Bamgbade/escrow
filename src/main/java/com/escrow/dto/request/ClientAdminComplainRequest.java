package com.escrow.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientAdminComplainRequest {
    private String sellerPhoneNumber;
    private String complainMessage;
}
