package com.escrow.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminEscrowRequest {
    private String messageSeller;
    private String clientPaymentStatus;
    private String clientPhoneNumber;
    private String sellerPhoneNumber;
}
