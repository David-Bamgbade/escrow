package com.escrow.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminEscrowResponse {
    private String notifySeller;
    private String clientPaymentStatus;
    private boolean paymentStatus;
}
