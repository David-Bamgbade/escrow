package com.escrow.service;


import com.escrow.dto.request.ClientComplainRequest;
import com.escrow.dto.request.EscrowPaymentRequest;
import com.escrow.dto.request.RegisterClientRequest;
import com.escrow.dto.request.SellerPaymentDetailsRequest;
import com.escrow.dto.response.ClientComplainResponse;
import com.escrow.dto.response.EscrowPaymentResponse;
import com.escrow.dto.response.RegisterClientResponse;
import com.escrow.dto.response.SellerPaymentDetailsResponse;

public interface ClientService {
    SellerPaymentDetailsResponse sendSellerDetails(SellerPaymentDetailsRequest request);

    EscrowPaymentResponse makePaymentToEscrow(EscrowPaymentRequest request);

    RegisterClientResponse signUp(RegisterClientRequest request);

    ClientComplainResponse makeComplain(ClientComplainRequest complainRequest);
}
