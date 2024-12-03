package com.escrow.dto.request;

import com.escrow.model.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminEscrowRequest {
    private String messageSeller;
    private PaymentStatus clientPaymentStatus;
}
