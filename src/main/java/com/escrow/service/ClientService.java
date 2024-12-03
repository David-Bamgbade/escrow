package com.escrow.service;


import com.escrow.dto.request.ClientEscrowPaymentRequest;
import com.escrow.dto.request.SellerPaymentDetailsRequest;
import com.escrow.dto.response.EscrowPaymentResponse;
import com.escrow.dto.response.SellerPaymentDetailsResponse;

public interface ClientService {
    SellerPaymentDetailsResponse sendSellerDetails(SellerPaymentDetailsRequest request);

    EscrowPaymentResponse makePaymentToEscrow(ClientEscrowPaymentRequest request);
}
