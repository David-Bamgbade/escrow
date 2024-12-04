package com.escrow.dto.response;

import com.escrow.model.BankName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SameSellerDetailsResponse {
    private BankName escrowBankName;
    private String escrowAccountNumber;
    private boolean success;
    private String message;
}
