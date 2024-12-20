package com.escrow.dto.response;

import com.escrow.model.BankName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EscrowPaymentResponse {
    private boolean success;
    private String message;
    private BankName escrowBankName;
    private String escrowAccountNumber;
}
