package com.escrow.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SellerPaymentDetailsResponse {
    private boolean success;
    private String message;
}